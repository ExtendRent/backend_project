package src.data.models.vehicle_entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controllers.vehicle.responses.CarResponse;
import src.data.models.base_entities.Vehicle;
import src.data.models.image_entities.CarImageEntity;
import src.data.models.paperwork_entities.rentalEntities.RentalEntity;
import src.data.models.vehicle_entities.vehicle_features.car_features.CarBodyTypeEntity;
import src.data.models.vehicle_entities.vehicle_features.car_features.CarModelEntity;
import src.data.models.vehicle_entities.vehicle_features.car_features.CarSegmentEntity;

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


    public CarResponse toModel() {
        return CarResponse.builder()
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