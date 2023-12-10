package source_files.data.models.baseEntities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.models.vehicleEntities.vehicleFeatures.Brand;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarModel;
import source_files.data.models.vehicleEntities.vehicleFeatures.Color;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass //Alt klasların database tablosuna buradaki kolonları eklemek için kullanılır.
@Table(name = "vehicles") //TODO kontrol edilecek. eğer hata yoksa vehicles tablosu iptal edilecek.
@Inheritance(strategy = InheritanceType.JOINED) // kendini extend eden her klasa kendi değişkenlerini eklemesini sağlar.
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //@JoinColumn(name = "available_driving_license_types") //TODO Driving license sisteme eklenecek.
    //List<DrivingLicenseType> availableDrivingLicenseTypes;


    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brandEntity;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModel modelEntity;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color colorEntity;

    @Column(name = "year")
    private int year;

    @Column(name = "details")
    private String details;

    @Column(name = "rental_price")
    private double rentalPrice;
}
