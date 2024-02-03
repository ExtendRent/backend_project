package source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import source_files.data.models.baseEntities.Item;
import source_files.data.models.vehicleEntities.CarEntity;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "images")
public class ImagesEntity extends Item {
    @JoinColumn(name = "car_id", unique = true)
    @OneToOne
    private CarEntity carEntity;

    //TODO linkler sığmıyor.
    @Column(name = "image_paths")
    private List<String> imagePaths;
}
