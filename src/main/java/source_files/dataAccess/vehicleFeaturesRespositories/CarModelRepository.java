package source_files.dataAccess.vehicleFeaturesRespositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;

import java.util.List;
import java.util.Optional;

public interface CarModelRepository extends JpaRepository<CarModelEntity, Integer> {
    boolean existsByName(String modelName);

    boolean existsByNameAndIdNot(String name, int id);

    Optional<CarModelEntity> findByName(String modelName);

    List<CarModelEntity> findAllByBrandEntity_Id(int brandId);

    List<CarModelEntity> findAllByIsDeletedFalse();

    List<CarModelEntity> findAllByIsDeletedTrue();
}
