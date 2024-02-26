package source_files.data.DTO.vehicleDTOs;

import lombok.*;

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
    int carSegmentEntityId;
    int carImageEntityId;

    String carSegmentEntityName;
    String vehicleStatusEntityName;
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
    String imageEntityImageUrl;
    boolean isDeleted;
    boolean isAvailable;
}
