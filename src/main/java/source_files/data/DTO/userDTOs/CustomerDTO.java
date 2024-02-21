package source_files.data.DTO.userDTOs;

import lombok.*;
import source_files.data.enums.types.userTypes.UserRole;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    int id;
    int drivingLicenseTypeId;
    String phoneNumber;
    String drivingLicenseNumber;
    String drivingLicenseTypeEntityName;
    String name;
    String surname;
    String emailAddress;
    String userImageEntityImageUrl;
    boolean isDeleted;
    String authority;
    String status;
}

