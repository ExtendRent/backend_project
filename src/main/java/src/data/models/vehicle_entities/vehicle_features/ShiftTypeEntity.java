package src.data.models.vehicle_entities.vehicle_features;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controllers.vehicle.responses.ShiftTypeResponse;
import src.data.models.base_entities.BaseEntity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(builderMethodName = "shiftTypeBuilder")
@Table(name = "shift_types")
public class ShiftTypeEntity extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;

    public ShiftTypeResponse toModel() {
        return ShiftTypeResponse.builder()
                .id(getId())
                .name(getName())
                .build();
    }
}
