package src.repository.vehicle.features.common.status;

import org.springframework.data.jpa.repository.JpaRepository;
import src.service.vehicle.features.common.status.model.DefaultVehicleStatus;

import java.util.Optional;

public interface VehicleStatusRepository extends JpaRepository<VehicleStatusEntity, Integer> {

    Optional<VehicleStatusEntity> findByVehicleStatus(DefaultVehicleStatus vehicleStatus);

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);
}
