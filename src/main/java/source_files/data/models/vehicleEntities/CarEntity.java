package source_files.data.models.vehicleEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.controllers.vehicle.dtos.CarDTO;
import source_files.data.models.baseEntities.Vehicle;
import source_files.data.models.imageEntities.CarImageEntity;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarSegmentEntity;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(builderMethodName = "carBuilder")
@Table(name = "cars")
public class CarEntity extends Vehicle {

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModelEntity carModelEntity;

    @ManyToOne
    @JoinColumn(name = "body_type_id")
    private CarBodyTypeEntity carBodyTypeEntity;

    @ManyToOne
    @JoinColumn(name = "car_segment_id")
    private CarSegmentEntity carSegmentEntity;

    @Column(name = "license_plate", unique = true)
    private String licensePlate;

    @Column(name = "kilometer")
    private int kilometer;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "image_id")
    private CarImageEntity carImageEntity;

    @OneToMany(mappedBy = "carEntity", fetch = FetchType.EAGER)
    private List<RentalEntity> rentalList;


    public CarDTO toModel() {
        return CarDTO.builder()
                .id(getId())
                .carModelEntityBrandEntityId(carModelEntity.getBrandEntity().getId())
                .carModelEntityId(carModelEntity.getId())
                .colorEntityId(getColorEntity().getId())
                .fuelTypeEntityId(getFuelTypeEntity().getId())
                .shiftTypeEntityId(getShiftTypeEntity().getId())
                .carBodyTypeEntityId(getCarBodyTypeEntity().getId())
                .expectedMinDrivingLicenseTypeId(getExpectedMinDrivingLicenseType().getId())
                .vehicleStatusEntityId(getVehicleStatusEntity().getId())
                .carSegmentEntityId(getCarSegmentEntity().getId())
                .carImageEntityId(getCarImageEntity().getId())

                .carSegmentEntityName(getCarSegmentEntity().getName())
                .vehicleStatusEntityName(getVehicleStatusEntity().getName())
                .carModelEntityBrandEntityName(getCarModelEntity().getBrandEntity().getName())
                .carModelEntityName(getCarModelEntity().getName())
                .colorEntityName(getColorEntity().getName())
                .carBodyTypeEntityName(getCarBodyTypeEntity().getName())
                .fuelTypeEntityName(getFuelTypeEntity().getName())
                .shiftTypeEntityName(getShiftTypeEntity().getName())
                .expectedMinDrivingLicenseTypeName(getExpectedMinDrivingLicenseType().getName())
                .imageEntityImageUrl(getCarImageEntity().getUrl())

                .details(getDetails())
                .rentalPrice(getRentalPrice())
                .licensePlate(getLicensePlate())
                .seat(getSeat())
                .luggage(getLuggage())
                .year(getYear())
                .kilometer(getKilometer())
                .isDeleted(getIsDeleted())
                .isAvailable(isAvailable())
                .build();
    }
}