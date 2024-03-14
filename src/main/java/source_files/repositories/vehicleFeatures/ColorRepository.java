package source_files.repositories.vehicleFeatures;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.vehicleEntities.vehicleFeatures.ColorEntity;

import java.util.List;

public interface ColorRepository extends JpaRepository<ColorEntity, Integer> {
    boolean existsByNameIgnoreCase(String name);

    List<ColorEntity> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);
}
