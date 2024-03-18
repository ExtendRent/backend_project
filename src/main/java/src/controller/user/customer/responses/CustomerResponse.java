package src.controller.user.customer.responses;

import lombok.*;

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

    @Override
    public String toString() {
        return "CustomerResponse{" +
                "id=" + id +
                ", drivingLicenseTypeId=" + drivingLicenseTypeId +
                ", userImageEntityId=" + userImageEntityId +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", drivingLicenseNumber='" + drivingLicenseNumber + '\'' +
                ", drivingLicenseTypeEntityName='" + drivingLicenseTypeEntityName + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                ", userImageEntityImageUrl='" + userImageEntityImageUrl + '\'' +
                ", isDeleted=" + isDeleted +
                ", authority='" + authority + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

