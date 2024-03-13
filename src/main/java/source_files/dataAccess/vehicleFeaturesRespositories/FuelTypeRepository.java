package source_files.dataAccess.vehicleFeaturesRespositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.vehicleEntities.vehicleFeatures.FuelTypeEntity;

import java.util.List;

public interface FuelTypeRepository extends JpaRepository<FuelTypeEntity, Integer> {
    List<FuelTypeEntity> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);

    boolean existsByNameIgnoreCase(String name);
}
