package src.data.models.vehicle_entities.vehicle_features.car_features;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controllers.vehicle.responses.CarModelResponse;
import src.data.models.base_entities.BaseEntity;
import src.data.models.vehicle_entities.vehicle_features.BrandEntity;

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

    public CarModelResponse toModel() {
        return CarModelResponse.builder()
                .id(getId())
                .brandEntityId(getBrandEntity().getId())
                .name(name)
                .brandEntityName(getBrandEntity().getName())
                .isDeleted(getIsDeleted())
                .build();
    }

}