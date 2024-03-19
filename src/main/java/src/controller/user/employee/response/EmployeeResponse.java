package src.controller.user.employee.response;

import lombok.*;
import src.service.user.model.UserRole;

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

    @Override
    public String toString() {
        return "EmployeeResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", salary=" + salary +
                ", userImageEntityImageUrl='" + userImageEntityImageUrl + '\'' +
                ", isDeleted=" + isDeleted +
                ", authority=" + authority +
                '}';
    }
}
