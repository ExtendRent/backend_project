package src.repository.vehicle.features.car.segment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controller.vehicle.features.car.segment.response.CarSegmentResponse;
import src.repository.BaseEntity;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder(builderMethodName = "carSegmentBuilder")
@Table(name = "car_segments")
public class CarSegmentEntity extends BaseEntity {

    @Column(name = "name", unique = true)
    private String name;

    public CarSegmentResponse toModel() {
        return CarSegmentResponse.builder()
                .id(getId())
                .name(getName())
                .build();
    }
}
