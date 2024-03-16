package src.repositories.vehicle_features;

import org.springframework.data.jpa.repository.JpaRepository;
import src.data.models.vehicle_entities.vehicle_features.ShiftTypeEntity;

import java.util.List;

public interface ShiftTypeRepository extends JpaRepository<ShiftTypeEntity, Integer> {

    List<ShiftTypeEntity> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);

    boolean existsByNameIgnoreCase(String name);

}
