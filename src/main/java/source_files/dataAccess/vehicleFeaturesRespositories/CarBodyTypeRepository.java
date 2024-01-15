package source_files.dataAccess.vehicleFeaturesRespositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;

import java.util.List;

public interface CarBodyTypeRepository extends JpaRepository<CarBodyTypeEntity, Integer> {
    List<CarBodyTypeEntity> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameAndIdNot(String name, int id);

    boolean existsByName(String name);

}
