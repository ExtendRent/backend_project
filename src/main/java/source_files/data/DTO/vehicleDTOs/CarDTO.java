package source_files.data.DTO.vehicleDTOs;

import lombok.*;
import source_files.data.types.itemTypes.DefaultDrivingLicenseType;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDTO {

    int id;
    String VehicleStatusEntityName;
    Boolean isLicenseTypeSuitable = true;
    String carModelEntityBrandEntityName;
    String carModelEntityName;
    String colorEntityName;
    int year;
    String carBodyTypeEntityName;
    String fuelTypeEntityName;
    String shiftTypeEntityName;
    int seat;
    int luggage;
    String details;
    double rentalPrice;
    String licensePlate;
    int kilometer;
    List<String> imagesEntityImagePaths;
    List<DefaultDrivingLicenseType> expectedDefaultDrivingLicenseTypes;
}
