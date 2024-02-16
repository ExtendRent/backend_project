package source_files.data.DTO;

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
