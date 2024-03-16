package src.services.vehicle_features.common_features.vehicle_status;

import src.controllers.vehicle.requests.vehicleFeatures.vehicleStatus.CreateVehicleStatusRequest;
import src.controllers.vehicle.requests.vehicleFeatures.vehicleStatus.UpdateVehicleStatusRequest;
import src.controllers.vehicle.responses.VehicleStatusResponse;
import src.data.enums.default_data_enums.status.DefaultVehicleStatus;

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
