package src.controller.user.responses;

import lombok.*;
import src.service.user.model.UserRole;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    int id;
    String name;
    String surname;
    String email;
    String userImageEntityUrl;
    UserRole authority;
    String status;
    boolean isDeleted;
}
