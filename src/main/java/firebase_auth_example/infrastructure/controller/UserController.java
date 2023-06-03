package firebase_auth_example.infrastructure.controller;

import firebase_auth_example.infrastructure.dto.UserDTO;
import firebaseauth.infrastructure.dto.FirebaseUser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping
  public ResponseEntity<UserDTO> save(@RequestBody UserDTO user) {
    return new ResponseEntity<>(user, HttpStatus.OK);
  }

  @PreAuthorize("hasRole('USER')")
  @GetMapping("/current")
  public ResponseEntity<UserDTO> getCurrentUser() {
    FirebaseUser user = (FirebaseUser) SecurityContextHolder
        .getContext()
        .getAuthentication()
        .getPrincipal();
    UserDTO userDTO = new UserDTO(user.getName(), user.getRole());
    return new ResponseEntity<>(userDTO, HttpStatus.OK);
  }
}
