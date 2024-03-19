package src.repository.vehicle.features.car.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controller.vehicle.features.car.model.response.CarModelResponse;
import src.repository.BaseEntity;
import src.repository.vehicle.features.common.brand.BrandEntity;

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