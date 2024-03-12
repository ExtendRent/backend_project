package source_files.dataAccess.vehicleFeaturesRespositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.vehicleEntities.vehicleFeatures.ShiftTypeEntity;

import java.util.List;

public interface ShiftTypeRepository extends JpaRepository<ShiftTypeEntity, Integer> {

    List<ShiftTypeEntity> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);
    boolean existsByNameIgnoreCase(String name);

}
