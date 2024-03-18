package src.controller.vehicle.car.responses;

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

    @Override
    public String toString() {
        return "CarResponse{" +
                "id=" + id +
                ", carModelEntityBrandEntityId=" + carModelEntityBrandEntityId +
                ", carModelEntityId=" + carModelEntityId +
                ", colorEntityId=" + colorEntityId +
                ", fuelTypeEntityId=" + fuelTypeEntityId +
                ", shiftTypeEntityId=" + shiftTypeEntityId +
                ", carBodyTypeEntityId=" + carBodyTypeEntityId +
                ", expectedMinDrivingLicenseTypeId=" + expectedMinDrivingLicenseTypeId +
                ", vehicleStatusEntityId=" + vehicleStatusEntityId +
                ", carSegmentEntityId=" + carSegmentEntityId +
                ", carImageEntityId=" + carImageEntityId +
                ", carSegmentEntityName='" + carSegmentEntityName + '\'' +
                ", vehicleStatusEntityName='" + vehicleStatusEntityName + '\'' +
                ", carModelEntityBrandEntityName='" + carModelEntityBrandEntityName + '\'' +
                ", carModelEntityName='" + carModelEntityName + '\'' +
                ", colorEntityName='" + colorEntityName + '\'' +
                ", carBodyTypeEntityName='" + carBodyTypeEntityName + '\'' +
                ", fuelTypeEntityName='" + fuelTypeEntityName + '\'' +
                ", shiftTypeEntityName='" + shiftTypeEntityName + '\'' +
                ", expectedMinDrivingLicenseTypeName='" + expectedMinDrivingLicenseTypeName + '\'' +
                ", imageEntityImageUrl='" + imageEntityImageUrl + '\'' +
                ", isLicenseTypeSuitable=" + isLicenseTypeSuitable +
                ", details='" + details + '\'' +
                ", rentalPrice=" + rentalPrice +
                ", licensePlate='" + licensePlate + '\'' +
                ", seat=" + seat +
                ", luggage=" + luggage +
                ", year=" + year +
                ", kilometer=" + kilometer +
                ", isDeleted=" + isDeleted +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
