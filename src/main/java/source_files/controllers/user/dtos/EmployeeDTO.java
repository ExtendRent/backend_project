package source_files.controllers.user.dtos;

import lombok.*;
import source_files.data.enums.types.userTypes.UserRole;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    int id;
    String name;
    String surname;
    String email;
    double salary;
    String userImageEntityImageUrl;
    boolean isDeleted;
    UserRole authority;
}
