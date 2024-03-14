package source_files.repositories.vehicleFeatures;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.enums.defaultDataEnums.Status.DefaultVehicleStatus;
import source_files.data.models.vehicleEntities.vehicleFeatures.VehicleStatusEntity;

import java.util.Optional;

public interface VehicleStatusRepository extends JpaRepository<VehicleStatusEntity, Integer> {

    Optional<VehicleStatusEntity> findByVehicleStatus(DefaultVehicleStatus vehicleStatus);

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);
}
