package source_files.data.models.vehicleEntities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.baseEntities.Vehicle;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ImagesEntity;

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


}