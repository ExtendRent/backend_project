package source_files.dataAccess.vehicleFeaturesRespositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;

import java.util.List;

public interface CarModelRepository extends JpaRepository<CarModelEntity, Integer> {
    boolean existsByName(String modelName);

    List<CarModelEntity> findAllByIsDeletedFalse();

    List<CarModelEntity> findAllByIsDeletedTrue();
}
