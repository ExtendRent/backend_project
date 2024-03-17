package src.repository.vehicle.features.car.segment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarSegmentRepository extends JpaRepository<CarSegmentEntity, Integer> {

    List<CarSegmentEntity> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);

    boolean existsByNameIgnoreCase(String name);
}
