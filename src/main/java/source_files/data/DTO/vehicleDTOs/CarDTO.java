package source_files.data.DTO.vehicleDTOs;

import lombok.*;
import source_files.data.types.itemTypes.DrivingLicenseType;
import source_files.data.types.itemTypes.FuelType;
import source_files.data.types.itemTypes.ShiftType;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO {

    int id;
    Boolean isLicenseTypeSuitable = true;
    String carModelEntityBrandEntityName;
    String carModelEntityName;
    String colorEntityName;
    int year;
    String carBodyTypeEntityName;
    String fuelType;
    String shiftType;
    int seat;
    int luggage;
    String details;
    double rentalPrice;
    String licensePlate;
    int kilometer;
    List<String> imagesEntityImagePaths;
    List<DrivingLicenseType> expectedDrivingLicenseTypes;
}
