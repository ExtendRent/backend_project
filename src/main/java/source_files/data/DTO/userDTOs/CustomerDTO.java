package source_files.data.DTO.userDTOs;

import lombok.*;
import source_files.data.types.userTypes.UserRole;

import java.util.List;

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
    List<UserRole> authorities;
}

