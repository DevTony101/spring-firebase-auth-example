package firebase_auth_example.infrastructure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {

  private String name;
  private String role;
}
