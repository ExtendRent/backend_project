package source_files.data.DTO.userDTOs;

import lombok.*;
import source_files.data.types.DrivingLicenseType;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {

    String phoneNumber;
    String drivingLicenseNumber;
    DrivingLicenseType drivingLicenseType;
    String name;
    String surname;
    String emailAddress;


}

