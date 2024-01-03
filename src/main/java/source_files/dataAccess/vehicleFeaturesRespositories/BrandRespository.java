package source_files.dataAccess.vehicleFeaturesRespositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;

import java.util.List;

public interface BrandRespository extends JpaRepository<BrandEntity, Integer> {
    List<BrandEntity> findAllByIsDeletedFalse();

    List<BrandEntity> findAllByIsDeletedTrue();

    boolean existsByName(String brandName);

    boolean existsByNameAndIdNot(String name, int id);

    BrandEntity findByName(String brandName);
}
