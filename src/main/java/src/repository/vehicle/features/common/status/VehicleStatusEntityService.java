package src.repository.vehicle.features.common.status;

import src.controller.vehicle.features.common.status.request.CreateVehicleStatusRequest;
import src.controller.vehicle.features.common.status.request.UpdateVehicleStatusRequest;
import src.service.vehicle.features.common.status.model.DefaultVehicleStatus;

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
