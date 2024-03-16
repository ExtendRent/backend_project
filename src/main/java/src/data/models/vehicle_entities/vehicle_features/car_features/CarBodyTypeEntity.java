package src.data.models.vehicle_entities.vehicle_features.car_features;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controllers.vehicle.responses.CarBodyTypeResponse;
import src.data.models.base_entities.BaseEntity;


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

    public CarBodyTypeResponse toModel() {
        return CarBodyTypeResponse.builder()
                .id(getId())
                .name(getName())
                .build();
    }
}