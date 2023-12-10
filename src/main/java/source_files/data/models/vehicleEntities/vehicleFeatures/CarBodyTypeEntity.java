package source_files.data.models.vehicleEntities.vehicleFeatures;

import jakarta.persistence.Column;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source_files.data.models.baseEntities.ItemEntity;


@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "car_body_types")
public class CarBodyTypeEntity extends ItemEntity {
    @Column(name = "name", unique = true)
    private String name;
}