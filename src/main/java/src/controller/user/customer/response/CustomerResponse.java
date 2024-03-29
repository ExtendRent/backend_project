package src.controller.user.customer.response;

import lombok.*;

@ToString
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {
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

