package src.controller.user.admin.responses;

import lombok.*;
import src.service.user.model.UserRole;

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

    @Override
    public String toString() {
        return "AdminResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", salary=" + salary +
                ", userImageEntityImageUrl='" + userImageEntityImageUrl + '\'' +
                ", isDeleted=" + isDeleted +
                ", authority=" + authority +
                '}';
    }
}
