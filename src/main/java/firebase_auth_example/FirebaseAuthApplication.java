package firebase_auth_example;

import firebaseauth.annotation.EnableFirebaseAuth;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableFirebaseAuth
@SpringBootApplication
public class FirebaseAuthApplication {

  public static void main(String[] args) {
    SpringApplication.run(FirebaseAuthApplication.class, args);
  }
}
