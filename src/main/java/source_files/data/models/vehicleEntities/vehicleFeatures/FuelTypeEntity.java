package source_files.data.models.vehicleEntities.vehicleFeatures;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.DTO.itemDTOs.FuelTypeDTO;
import source_files.data.models.baseEntities.BaseEntity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(builderMethodName = "fuelTypeBuilder")
@Table(name = "fuel_types")
public class FuelTypeEntity extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;

    public FuelTypeDTO toModel() {
        return FuelTypeDTO.builder()
                .id(getId())
                .name(getName())
                .isDeleted(getIsDeleted())
                .build();
    }
}
