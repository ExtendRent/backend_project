package src.repository.vehicle.features.car.body;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controller.vehicle.features.car.body.responses.CarBodyTypeResponse;
import src.repository.BaseEntity;


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