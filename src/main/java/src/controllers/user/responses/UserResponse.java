package src.controllers.user.responses;

import lombok.*;
import src.data.enums.types.user_types.UserRole;

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
