package src.service.vehicle.features.common.status;

import src.controller.vehicle.features.common.status.requests.CreateVehicleStatusRequest;
import src.controller.vehicle.features.common.status.requests.UpdateVehicleStatusRequest;
import src.controller.vehicle.features.common.status.responses.VehicleStatusResponse;
import src.service.vehicle.features.common.status.model.DefaultVehicleStatus;

import java.util.List;

public interface VehicleStatusService {
    void create(CreateVehicleStatusRequest createVehicleStatusRequest);

    VehicleStatusResponse update(UpdateVehicleStatusRequest updateVehicleStatusRequest);

    VehicleStatusResponse getById(int id);

    List<VehicleStatusResponse> getAll();

    VehicleStatusResponse getByStatus(DefaultVehicleStatus status);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);
}
