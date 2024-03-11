package source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.DTO.itemDTOs.CarBodyTypeDTO;
import source_files.data.models.baseEntities.BaseEntity;


@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(builderMethodName = "carBodyTypeBuilder")
@Table(name = "car_body_types")
public class CarBodyTypeEntity extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;

    public CarBodyTypeDTO toModel() {
        return CarBodyTypeDTO.builder()
                .id(getId())
                .name(getName())
                .build();
    }
}