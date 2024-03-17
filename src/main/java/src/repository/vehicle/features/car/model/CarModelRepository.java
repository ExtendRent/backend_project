package src.repository.vehicle.features.car.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarModelRepository extends JpaRepository<CarModelEntity, Integer> {
    boolean existsByNameIgnoreCase(String modelName);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);

    Optional<CarModelEntity> findByName(String modelName);


    List<CarModelEntity> findAllByBrandEntity_Id(int brandId);


    List<CarModelEntity> findAllByIsDeleted(boolean isDeleted);
}
