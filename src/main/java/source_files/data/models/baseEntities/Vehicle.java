package source_files.data.models.baseEntities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.models.vehicleEntities.vehicleFeatures.BrandEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.ColorEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass //Alt klasların database tablosuna buradaki kolonları eklemek için kullanılır.
@Inheritance(strategy = InheritanceType.JOINED) // kendini extend eden her klasa kendi değişkenlerini eklemesini sağlar.
public class Vehicle extends BaseEntity {


    //@JoinColumn(name = "available_driving_license_types") //TODO Driving license sisteme eklenecek.
    //List<DrivingLicenseType> availableDrivingLicenseTypes;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brandEntity;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private ColorEntity colorEntity;

    @Column(name = "year")
    private int year;

    @Column(name = "details")
    private String details;

    @Column(name = "rental_price")
    private double rentalPrice;

}
