package src.repositories.vehicle_features;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import src.data.models.vehicle_entities.vehicle_features.BrandEntity;

import java.util.List;

public interface BrandRepository extends JpaRepository<BrandEntity, Integer> {
    @Transactional
    List<BrandEntity> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameIgnoreCase(String brandName);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);
}
