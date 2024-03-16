package src.controllers.item.responses;

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
}
