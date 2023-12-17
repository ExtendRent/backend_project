package source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.baseEntities.Item;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
//@SuperBuilder
@Table(name = "car_models")
public class CarModelEntity extends Item {

    @Column(name = "name", unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private BrandEntity brandEntity;

}