package source_files.data.models.vehicleEntities.vehicleFeatures;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.DTO.itemDTOs.ShiftTypeDTO;
import source_files.data.models.baseEntities.BaseEntity;

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

    public ShiftTypeDTO toModel() {
        return ShiftTypeDTO.builder()
                .id(getId())
                .name(getName())
                .build();
    }
}
