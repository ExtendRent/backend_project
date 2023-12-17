package source_files.dataAccess.vehicleFeaturesRespositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.ColorEntity;

public interface ColorRepository extends JpaRepository<ColorEntity, Integer> {
    boolean existsByName(String colorName);
}
