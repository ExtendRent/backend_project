package source_files.data.models.vehicleEntities.vehicleFeatures;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.enums.Status.DefaultVehicleStatus;
import source_files.data.models.baseEntities.Item;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "vehicle_statuses")
public class VehicleStatusEntity extends Item {

    @Column(name = "name")
    private String name;

    @Column(name = "vehicle_status")
    @Enumerated(EnumType.STRING)
    private DefaultVehicleStatus vehicleStatus;
}
