package source_files.data.DTO.vehicleDTOs;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDTO {

    int id;
    int carModelEntityBrandEntityId;
    int carModelEntityId;
    int colorEntityId;
    int fuelTypeEntityId;
    int shiftTypeEntityId;
    int carBodyTypeEntityId;
    int expectedMinDrivingLicenseTypeId;
    int vehicleStatusEntityId;

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
    String expectedMinDrivingLicenseTypeName;
    List<String> imagesEntityImagePaths;

}
