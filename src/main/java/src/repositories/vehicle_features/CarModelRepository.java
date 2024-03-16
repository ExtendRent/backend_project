package src.repositories.vehicle_features;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import src.data.models.vehicle_entities.vehicle_features.car_features.CarModelEntity;

import java.util.List;
import java.util.Optional;

public interface CarModelRepository extends JpaRepository<CarModelEntity, Integer> {
    boolean existsByNameIgnoreCase(String modelName);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);

    Optional<CarModelEntity> findByName(String modelName);

    @Transactional
    List<CarModelEntity> findAllByBrandEntity_Id(int brandId);

    @Transactional
    List<CarModelEntity> findAllByIsDeleted(boolean isDeleted);
}
