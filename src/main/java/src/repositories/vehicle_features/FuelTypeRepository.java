package src.repositories.vehicle_features;

import org.springframework.data.jpa.repository.JpaRepository;
import src.data.models.vehicle_entities.vehicle_features.FuelTypeEntity;

import java.util.List;

public interface FuelTypeRepository extends JpaRepository<FuelTypeEntity, Integer> {
    List<FuelTypeEntity> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);

    boolean existsByNameIgnoreCase(String name);
}
