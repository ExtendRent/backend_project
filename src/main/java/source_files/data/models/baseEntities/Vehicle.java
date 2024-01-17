package source_files.data.models.baseEntities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ColorEntity;
import source_files.data.types.itemTypes.DrivingLicenseType;
import source_files.data.types.itemTypes.FuelType;
import source_files.data.types.itemTypes.ShiftType;
import source_files.data.types.itemTypes.VehicleType;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@SuperBuilder
@MappedSuperclass //Alt klasların database tablosuna buradaki kolonları eklemek için kullanılır.
@Inheritance(strategy = InheritanceType.JOINED) // kendini extend eden her klasa kendi değişkenlerini eklemesini sağlar.
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

    @Column(name = "fuel_type")
    @Enumerated(EnumType.STRING)
    private FuelType fuelType;

    @Column(name = "shift_type")
    @Enumerated(EnumType.STRING)
    private ShiftType shiftType;

    @Column(name = "rental_price")
    private double rentalPrice;

    @Column(name = "is_available")
    private Boolean isAvailable = true;

    @Column(name = "availability_date")
    private LocalDate availabilityDate;

    @Column(name = "expected_driving_license_types")
    private List<DrivingLicenseType> expectedDrivingLicenseTypes; //-> kullanıcıdan beklenen ehliyet sınıfları.

    @Column(name = "vehicle_type")
    @Enumerated(EnumType.STRING)
    private VehicleType vehicleType;
}
