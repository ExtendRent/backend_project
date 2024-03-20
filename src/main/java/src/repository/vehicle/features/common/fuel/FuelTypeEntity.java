package src.repository.vehicle.features.common.fuel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import src.controller.vehicle.features.common.fuel.response.FuelTypeResponse;
import src.core.BaseEntity;

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

    public FuelTypeResponse toModel() {
        return FuelTypeResponse.builder()
                .id(getId())
                .name(getName())
                .isDeleted(getIsDeleted())
                .build();
    }
}
