package source_files.dataAccess.vehicleFeaturesRespositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;

import java.util.List;
import java.util.Optional;

public interface CarModelRepository extends JpaRepository<CarModelEntity, Integer> {
    boolean existsByName(String modelName);

    Optional<CarModelEntity> findByName(String modelName);

    List<CarModelEntity> findAllByBrandId(int id);

    List<CarModelEntity> findAllByIsDeletedFalse();

    List<CarModelEntity> findAllByIsDeletedTrue();
}
