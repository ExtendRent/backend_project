package src.repository.vehicle.features.common.brand;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BrandRepository extends JpaRepository<BrandEntity, Integer> {

    List<BrandEntity> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameIgnoreCase(String brandName);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);
}
