package source_files.data.models.vehicleEntities.vehicleFeatures;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.DTO.itemDTOs.ColorDTO;
import source_files.data.models.baseEntities.BaseEntity;

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

    public ColorDTO toModel() {
        return ColorDTO.builder()
                .id(getId())
                .name(getName())
                .build();
    }
}