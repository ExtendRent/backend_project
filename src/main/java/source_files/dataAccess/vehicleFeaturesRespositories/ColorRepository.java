package source_files.dataAccess.vehicleFeaturesRespositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.vehicleEntities.vehicleFeatures.ColorEntity;

import java.util.List;

public interface ColorRepository extends JpaRepository<ColorEntity, Integer> {
    boolean existsByName(String name);

    List<ColorEntity> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameAndIdNot(String name, int id);
}
