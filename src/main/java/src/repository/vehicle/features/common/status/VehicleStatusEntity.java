package src.repository.vehicle.features.common.status;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controller.vehicle.features.common.status.response.VehicleStatusResponse;
import src.core.BaseEntity;
import src.service.vehicle.features.common.status.model.DefaultVehicleStatus;

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
