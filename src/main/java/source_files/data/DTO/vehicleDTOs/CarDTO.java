package source_files.data.DTO.vehicleDTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.types.itemTypes.DrivingLicenseType;

import java.time.LocalDate;
import java.util.List;

//@Builder
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
    String fuelTypeEntityName;
    String shiftTypeEntityName;
    int seat;
    int luggage;
    String details;
    double rentalPrice;
    String licensePlate;
    int kilometer;
    List<String> imagesEntityImagePaths;
    boolean isAvailable;
    LocalDate availabilityDate;
    List<DrivingLicenseType> expectedDrivingLicenseTypes;
}
