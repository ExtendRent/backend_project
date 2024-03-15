package source_files.controllers.user.dtos;

import lombok.*;
import source_files.data.enums.types.userTypes.UserRole;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    int id;
    String name;
    String surname;
    String email;
    String userImageEntityUrl;
    UserRole authority;
    String status;
    boolean isDeleted;
}
