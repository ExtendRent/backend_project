package src.controller.user.employee.response;

import lombok.*;
import src.service.user.model.UserRole;

@ToString
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
