package source_files.controllers.user.dtos;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
    int id;
    int drivingLicenseTypeId;
    int userImageEntityId;
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

