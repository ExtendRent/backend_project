package source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import source_files.data.DTO.itemDTOs.CarModelDTO;
import source_files.data.models.baseEntities.BaseEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.BrandEntity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(builderMethodName = "carModelBuilder")
@Table(name = "car_models")
public class CarModelEntity extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "brandEntity_id")
    private BrandEntity brandEntity;

    public CarModelDTO toModel() {
        return CarModelDTO.builder()
                .id(getId())
                .brandEntityId(getBrandEntity().getId())
                .name(name)
                .brandEntityName(getBrandEntity().getName())
                .isDeleted(getIsDeleted())
                .build();
    }

}