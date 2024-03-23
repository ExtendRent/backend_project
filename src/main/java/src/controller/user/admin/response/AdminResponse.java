package src.controller.user.admin.response;

import lombok.*;
import src.service.user.model.UserRole;

@ToString
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
