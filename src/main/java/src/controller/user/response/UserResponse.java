package src.controller.user.response;

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

    @Override
    public String toString() {
        return "UserResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", userImageEntityUrl='" + userImageEntityUrl + '\'' +
                ", authority=" + authority +
                ", status='" + status + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
