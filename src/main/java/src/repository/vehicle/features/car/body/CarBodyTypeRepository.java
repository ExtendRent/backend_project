package src.repository.vehicle.features.car.body;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarBodyTypeRepository extends JpaRepository<CarBodyTypeEntity, Integer> {
    List<CarBodyTypeEntity> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);

    boolean existsByNameIgnoreCase(String name);

}
