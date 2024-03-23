package src.controller.license.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DrivingLicenseTypeResponse {
    int id;
    String name;
    String description;
    int licenseLevel;
    boolean isDeleted;

}
