package src.controllers.vehicle.responses;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarResponse {

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
    String carModelEntityBrandEntityName;
    String carModelEntityName;
    String colorEntityName;

    String carBodyTypeEntityName;
    String fuelTypeEntityName;
    String shiftTypeEntityName;
    String expectedMinDrivingLicenseTypeName;
    String imageEntityImageUrl;

    Boolean isLicenseTypeSuitable = true;

    String details;
    double rentalPrice;
    String licensePlate;
    int seat;
    int luggage;
    int year;
    int kilometer;
    boolean isDeleted;
    boolean isAvailable;
}
