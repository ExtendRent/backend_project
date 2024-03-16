package src.controllers.user.responses;

import lombok.*;
import src.data.enums.types.user_types.UserRole;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminResponse {
    int id;
    String name;
    String surname;
    String email;
    String phoneNumber;
    double salary;
    String userImageEntityImageUrl;
    boolean isDeleted;
    UserRole authority;
}
