package src.controller.user.response;

import lombok.*;
import src.service.user.model.UserRole;

@ToString
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
