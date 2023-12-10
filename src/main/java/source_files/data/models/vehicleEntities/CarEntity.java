package source_files.data.models.vehicleEntities;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source_files.data.models.baseEntities.VehicleEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarBodyType;


@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "cars")
public class CarEntity extends VehicleEntity {


    @ManyToOne
    @JoinColumn(name = "body_type_id")
    private CarBodyType bodyTypeEntity;

    @Column(name = "license_plate", unique = true)
    private String licensePlate;
}