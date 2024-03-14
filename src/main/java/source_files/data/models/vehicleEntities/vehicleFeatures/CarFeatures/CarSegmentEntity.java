package source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.controllers.vehicle.dtos.CarSegmentDTO;
import source_files.data.models.baseEntities.BaseEntity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(builderMethodName = "carSegmentBuilder")
@Table(name = "car_segments")
public class CarSegmentEntity extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;

    public CarSegmentDTO toModel() {
        return CarSegmentDTO.builder()
                .id(getId())
                .name(getName())
                .build();
    }
}
