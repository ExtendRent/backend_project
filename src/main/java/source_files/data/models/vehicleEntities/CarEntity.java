package source_files.data.models.vehicleEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.models.baseEntities.Vehicle;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ImagesEntity;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cars")
public class CarEntity extends Vehicle {

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModelEntity carModelEntity;

    @ManyToOne
    @JoinColumn(name = "body_type_id")
    private CarBodyTypeEntity carBodyTypeEntity;

    @Column(name = "license_plate", unique = true)
    private String licensePlate;

    @Column(name = "kilometer")
    private int kilometer;

    @OneToOne(mappedBy = "carEntity"
            , cascade = {CascadeType.REMOVE, CascadeType.DETACH, CascadeType.REFRESH, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private ImagesEntity imagesEntity;

    @OneToMany(mappedBy = "carEntity")
    private List<RentalEntity> rentalList;

    //todo : shot tipinde minFindexRate diye bir alan ekledi hoca araba kiralamalarda kullanılıyormuş

    public CarDTO convertToDTO() {
        return CarDTO.builder()
                .id(this.getId())
                .carModelEntityBrandEntityName(this.getCarModelEntity().getBrandEntity().getName())
                .carModelEntityName(this.getCarModelEntity().getName())
                .carBodyTypeEntityName(this.getCarBodyTypeEntity().getName())
                .licensePlate(this.getLicensePlate())
                .kilometer(this.getKilometer())
                .imagesEntityImagePaths(this.getImagesEntity().getImagePaths())
                .year(this.getYear())
                .seat(this.getSeat())
                .rentalPrice(this.getRentalPrice())
                .details(this.getDetails())
                .luggage(this.getLuggage())
                .expectedDefaultDrivingLicenseTypes(this.getExpectedDefaultDrivingLicenseTypes())
                .colorEntityName(this.getColorEntity().getName())
                .fuelTypeEntityName(this.getFuelTypeEntity().getName())
                .shiftTypeEntityName(this.getShiftTypeEntity().getName())
                .build();
    }

    public UpdateCarRequest convertToUpdateRequest() {
        return UpdateCarRequest.builder()
                .id(this.getId())
                .brandEntityId(this.getCarModelEntity().getBrandEntity().getId())
                .carModelEntityId(this.getCarModelEntity().getId())
                .carBodyTypeEntityId(this.getCarBodyTypeEntity().getId())
                .licensePlate(this.getLicensePlate())
                .kilometer(this.getKilometer())
                .imagePaths(this.getImagesEntity().getImagePaths())
                .year(this.getYear())
                .seat(this.getSeat())
                .rentalPrice(this.getRentalPrice())
                .details(this.getDetails())
                .luggage(this.getLuggage())
                .expectedDefaultDrivingLicenseTypes(this.getExpectedDefaultDrivingLicenseTypes())
                .colorEntityId(this.getColorEntity().getId())
                .fuelTypeEntityId(this.getFuelTypeEntity().getId())
                .shiftTypeEntityId(this.getShiftTypeEntity().getId())
                .build();
    }

}