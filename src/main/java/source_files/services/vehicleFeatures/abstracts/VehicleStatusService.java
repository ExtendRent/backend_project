package source_files.services.vehicleFeatures.abstracts;

import source_files.controllers.vehicle.dtos.VehicleStatusDTO;
import source_files.controllers.vehicle.requests.vehicleFeatures.vehicleStatus.CreateVehicleStatusRequest;
import source_files.controllers.vehicle.requests.vehicleFeatures.vehicleStatus.UpdateVehicleStatusRequest;
import source_files.data.enums.defaultDataEnums.Status.DefaultVehicleStatus;

import java.util.List;

public interface VehicleStatusService {
    void create(CreateVehicleStatusRequest createVehicleStatusRequest);

    VehicleStatusDTO update(UpdateVehicleStatusRequest updateVehicleStatusRequest);

    VehicleStatusDTO getById(int id);

    List<VehicleStatusDTO> getAll();

    VehicleStatusDTO getByStatus(DefaultVehicleStatus status);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);
}
