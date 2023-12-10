package source_files.data.models.baseEntities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import source_files.data.models.vehicleEntities.vehicleFeatures.BrandEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.ColorEntity;

import java.util.Date;

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

    @Builder.Default
    @Column(name = "is_deleted")
    private boolean isDeleted = false;

    @LastModifiedDate
    @Column(name = "last_modified")
    private Date lastModified;

    @CreatedDate
    @Column(name = "created_date")
    private Date createdDate;

}
