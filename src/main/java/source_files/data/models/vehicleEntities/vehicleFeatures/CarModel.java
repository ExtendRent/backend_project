package source_files.data.models.vehicleEntities.vehicleFeatures;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import source_files.data.models.baseEntities.Item;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Table(name = "car_models")
public class CarModel extends Item {

    @Column(name = "name", unique = true)
    private String name;

}