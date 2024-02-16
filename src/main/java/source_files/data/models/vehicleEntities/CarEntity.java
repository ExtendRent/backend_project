package source_files.data.models.vehicleEntities;

import jakarta.persistence.*;
import lombok.*;
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
@Builder
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

}