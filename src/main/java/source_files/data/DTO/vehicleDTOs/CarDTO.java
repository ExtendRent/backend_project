package source_files.data.DTO.vehicleDTOs;

import lombok.*;
import source_files.data.types.DrivingLicenseType;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    int id;
    String carModelEntityBrandEntityName;
    String carModelEntityName;
    String colorEntityName;
    int year;
    String carBodyTypeEntityName;
    int seat;
    int luggage;
    String details;
    double rentalPrice;
    String licensePlate;
    int kilometer;
    List<String> imagesEntityImagePaths;
    List<DrivingLicenseType> expectedDrivingLicenseTypes;

}
