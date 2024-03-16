package src.data.models.vehicle_entities.vehicle_features;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controllers.vehicle.responses.VehicleStatusResponse;
import src.data.enums.default_data_enums.status.DefaultVehicleStatus;
import src.data.models.base_entities.BaseEntity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(builderMethodName = "vehicleStatusBuilder")
@Table(name = "vehicle_statuses")
public class VehicleStatusEntity extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "vehicle_status")
    @Enumerated(EnumType.STRING)
    private DefaultVehicleStatus vehicleStatus;

    public VehicleStatusResponse toModel() {
        return VehicleStatusResponse.builder()
                .id(getId())
                .name(name)
                .isDeleted(getIsDeleted())
                .build();
    }
}
