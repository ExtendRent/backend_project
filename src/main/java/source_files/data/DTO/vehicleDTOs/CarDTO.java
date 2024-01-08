package source_files.data.DTO.vehicleDTOs;

import lombok.*;
import source_files.data.types.DrivingLicenseType;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {

    int id;
    String brandEntityName;
    String modelEntityName;
    String colorEntityName;
    int year;
    String carBodyTypeEntityName;
    String details;
    double rentalPrice;
    String licensePlate;
    int kilometer;
    List<DrivingLicenseType> expectedDrivingLicenseTypes;
}
