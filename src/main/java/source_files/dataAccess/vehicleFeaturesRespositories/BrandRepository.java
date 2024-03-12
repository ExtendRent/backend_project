package source_files.dataAccess.vehicleFeaturesRespositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import source_files.data.models.vehicleEntities.vehicleFeatures.BrandEntity;

import java.util.List;

public interface BrandRepository extends JpaRepository<BrandEntity, Integer> {
    @Transactional
    List<BrandEntity> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameIgnoreCase(String brandName);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);
}
