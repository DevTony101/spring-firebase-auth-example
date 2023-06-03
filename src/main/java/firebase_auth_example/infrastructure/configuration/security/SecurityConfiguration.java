package firebase_auth_example.infrastructure.configuration.security;

import firebaseauth.FirebaseAuthFilter;
import firebaseauth.security.SimpleFirebaseSecurityConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfiguration implements SimpleFirebaseSecurityConfiguration {

  private static final String[] SWAGGER_AUTH_WHITELIST = {
      "/swagger-ui/**",
      "/v3/api-docs/**",
      "/swagger-resources/**",
      "/swagger-ui.html",
      "/webjars/**",
  };

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.cors().configurationSource(corsConfigurationSource()).and().csrf().disable().formLogin().disable()
        .httpBasic().disable().exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint())
        .and().authorizeRequests()
        .antMatchers(SWAGGER_AUTH_WHITELIST).permitAll()
        .antMatchers(HttpMethod.OPTIONS, "/**").permitAll().anyRequest().authenticated().and()
        .addFilterBefore(new FirebaseAuthFilter(), UsernamePasswordAuthenticationFilter.class)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    return http.build();
  }

  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return web -> web.ignoring().antMatchers(SWAGGER_AUTH_WHITELIST);
  }
}
