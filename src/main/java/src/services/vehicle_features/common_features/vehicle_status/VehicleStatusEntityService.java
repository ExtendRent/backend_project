package src.services.vehicle_features.common_features.vehicle_status;

import src.controllers.vehicle.requests.vehicleFeatures.vehicleStatus.CreateVehicleStatusRequest;
import src.controllers.vehicle.requests.vehicleFeatures.vehicleStatus.UpdateVehicleStatusRequest;
import src.data.enums.default_data_enums.status.DefaultVehicleStatus;
import src.data.models.vehicle_entities.vehicle_features.VehicleStatusEntity;

import java.util.List;

public interface VehicleStatusEntityService {
    VehicleStatusEntity create(CreateVehicleStatusRequest createVehicleStatusRequest);

    VehicleStatusEntity update(UpdateVehicleStatusRequest updateVehicleStatusRequest);

    VehicleStatusEntity update(VehicleStatusEntity vehicleStatusEntity);

    VehicleStatusEntity getById(int id);

    List<VehicleStatusEntity> getAll();

    VehicleStatusEntity getByStatus(DefaultVehicleStatus status);

    void delete(VehicleStatusEntity vehicleStatusEntity);
}
