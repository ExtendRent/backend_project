package source_files.controllers.item.dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DrivingLicenseTypeDTO {
    int id;
    String name;
    String description;
    int licenseLevel;
    boolean isDeleted;
}
