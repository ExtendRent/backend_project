package source_files.controllers.user.dtos;

import lombok.*;
import source_files.data.enums.types.userTypes.UserRole;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AdminDTO {
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
