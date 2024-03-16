package src.repositories.vehicle_features;

import org.springframework.data.jpa.repository.JpaRepository;
import src.data.enums.default_data_enums.status.DefaultVehicleStatus;
import src.data.models.vehicle_entities.vehicle_features.VehicleStatusEntity;

import java.util.Optional;

public interface VehicleStatusRepository extends JpaRepository<VehicleStatusEntity, Integer> {

    Optional<VehicleStatusEntity> findByVehicleStatus(DefaultVehicleStatus vehicleStatus);

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);
}
