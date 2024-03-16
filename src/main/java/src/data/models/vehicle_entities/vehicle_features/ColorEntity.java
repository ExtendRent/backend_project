package src.data.models.vehicle_entities.vehicle_features;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controllers.vehicle.responses.ColorResponse;
import src.data.models.base_entities.BaseEntity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(builderMethodName = "colorBuilder")
@Table(name = "colors")
public class ColorEntity extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;

    public ColorResponse toModel() {
        return ColorResponse.builder()
                .id(getId())
                .name(getName())
                .build();
    }
}