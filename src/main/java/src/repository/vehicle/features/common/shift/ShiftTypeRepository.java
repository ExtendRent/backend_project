package src.repository.vehicle.features.common.shift;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShiftTypeRepository extends JpaRepository<ShiftTypeEntity, Integer> {

    List<ShiftTypeEntity> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);

    boolean existsByNameIgnoreCase(String name);

}
