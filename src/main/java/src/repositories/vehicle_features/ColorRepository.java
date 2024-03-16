package src.repositories.vehicle_features;

import org.springframework.data.jpa.repository.JpaRepository;
import src.data.models.vehicle_entities.vehicle_features.ColorEntity;

import java.util.List;

public interface ColorRepository extends JpaRepository<ColorEntity, Integer> {
    boolean existsByNameIgnoreCase(String name);

    List<ColorEntity> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);
}
