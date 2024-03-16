package src.data.models.base_entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.data.enums.types.item_types.VehicleType;
import src.data.models.DrivingLicenseTypeEntity;
import src.data.models.vehicle_entities.vehicle_features.ColorEntity;
import src.data.models.vehicle_entities.vehicle_features.FuelTypeEntity;
import src.data.models.vehicle_entities.vehicle_features.ShiftTypeEntity;
import src.data.models.vehicle_entities.vehicle_features.VehicleStatusEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass //Alt klasların database tablosuna buradaki kolonları eklemek için kullanılır.
@SuperBuilder(builderMethodName = "vehicleBuilder")
public class Vehicle extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "color_id")
    private ColorEntity colorEntity;

    @Column(name = "year")
    private int year;

    @Column(name = "details")
    private String details;

    @Column(name = "seat")
    private int seat;

    @Column(name = "luggage")
    private int luggage;

    @ManyToOne
    @JoinColumn(name = "fuel_type_id")
    private FuelTypeEntity fuelTypeEntity;

    @ManyToOne
    @JoinColumn(name = "shift_type_id")
    private ShiftTypeEntity shiftTypeEntity;

    @Column(name = "rental_price")
    private double rentalPrice;

    @ManyToOne
    @JoinColumn(name = "vehicle_status_id")
    private VehicleStatusEntity vehicleStatusEntity;

    @Column(name = "is_available")
    private boolean isAvailable;

    @ManyToOne
    @JoinColumn(name = "expected_min_driving_license_type_id")
    private DrivingLicenseTypeEntity expectedMinDrivingLicenseType; //-> kullanıcıdan beklenen minimum ehliyet sınıfı.

    @Column(name = "vehicle_type")
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
}
