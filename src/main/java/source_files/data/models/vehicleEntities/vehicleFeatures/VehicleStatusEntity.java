package source_files.data.models.vehicleEntities.vehicleFeatures;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.DTO.itemDTOs.VehicleStatusDTO;
import source_files.data.enums.defaultDataEnums.Status.DefaultVehicleStatus;
import source_files.data.models.baseEntities.BaseEntity;

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

    public VehicleStatusDTO toModel() {
        return VehicleStatusDTO.builder()
                .name(name)
                .isDeleted(getIsDeleted())
                .build();
    }
}
