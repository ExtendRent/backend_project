package source_files.data.DTO.userDTOs;

import lombok.Builder;
import source_files.data.types.DrivingLicenseType;
import source_files.data.types.UserType;

@Builder
public record CustomerDTO(String phoneNumber,
                          String drivingLicenseNumber,
                          DrivingLicenseType drivingLicenseType,
                          String name,
                          String surname,
                          String email
                          ) {
}
