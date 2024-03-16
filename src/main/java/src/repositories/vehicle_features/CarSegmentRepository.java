package src.repositories.vehicle_features;

import org.springframework.data.jpa.repository.JpaRepository;
import src.data.models.vehicle_entities.vehicle_features.car_features.CarSegmentEntity;

import java.util.List;

public interface CarSegmentRepository extends JpaRepository<CarSegmentEntity, Integer> {

    List<CarSegmentEntity> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);

    boolean existsByNameIgnoreCase(String name);
}
