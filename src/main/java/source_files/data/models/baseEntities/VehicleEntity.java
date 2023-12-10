package source_files.data.models.baseEntities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source_files.data.models.vehicleEntities.vehicleFeatures.BrandEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarModelEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.ColorEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
//@Table(name = "vehicles") //TODO kontrol edilecek. eğer hata yoksa vehicles tablosu iptal edilecek.
public class VehicleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    //@JoinColumn(name = "available_driving_license_types") //TODO Driving license sisteme eklenecek.
    //List<DrivingLicenseType> availableDrivingLicenseTypes;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brandEntity;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModelEntity modelEntity;

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
