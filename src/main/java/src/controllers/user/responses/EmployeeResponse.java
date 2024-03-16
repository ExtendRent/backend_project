package src.controllers.user.responses;

import lombok.*;
import src.data.enums.types.user_types.UserRole;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeResponse {
    int id;
    String name;
    String surname;
    String email;
    double salary;
    String userImageEntityImageUrl;
    boolean isDeleted;
    UserRole authority;
}
