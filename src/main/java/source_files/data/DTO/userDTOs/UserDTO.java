package source_files.data.DTO.userDTOs;

import lombok.*;
import source_files.data.enums.defaultDataEnums.Status.DefaultUserStatus;
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
    DefaultUserStatus status;
    boolean isDeleted;
}
