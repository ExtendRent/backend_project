package source_files.dataAccess.vehicleFeaturesRespositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;

public interface CarBodyTypeRepository extends JpaRepository<CarBodyTypeEntity, Integer> {


}
