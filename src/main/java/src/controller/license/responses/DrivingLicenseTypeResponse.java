package src.controller.license.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrivingLicenseTypeResponse {
    int id;
    String name;
    String description;
    int licenseLevel;
    boolean isDeleted;

    @Override
    public String toString() {
        return "DrivingLicenseTypeResponse{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", licenseLevel=" + licenseLevel +
                ", isDeleted=" + isDeleted +
                '}';
    }
}
