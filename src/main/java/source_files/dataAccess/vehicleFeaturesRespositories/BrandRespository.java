package source_files.dataAccess.vehicleFeaturesRespositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;

public interface BrandRespository extends JpaRepository<BrandEntity, Integer> {
}
