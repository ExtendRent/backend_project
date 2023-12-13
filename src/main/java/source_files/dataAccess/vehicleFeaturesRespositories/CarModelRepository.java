package source_files.dataAccess.vehicleFeaturesRespositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;

public interface CarModelRepository extends JpaRepository<CarModelEntity, Integer> {
}
