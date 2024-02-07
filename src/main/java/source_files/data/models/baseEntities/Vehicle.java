package source_files.data.models.baseEntities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.DrivingLicenseTypeEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.ColorEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.FuelTypeEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.ShiftTypeEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.VehicleStatusEntity;
import source_files.data.types.itemTypes.VehicleType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@SuperBuilder
@MappedSuperclass //Alt klasların database tablosuna buradaki kolonları eklemek için kullanılır.
//@Inheritance(strategy = InheritanceType.JOINED) // kendini extend eden her klasa kendi değişkenlerini eklemesini sağlar.
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
