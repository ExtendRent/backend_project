package source_files.services.vehicleFeaturesServices.abstracts;

import source_files.data.DTO.itemDTOs.VehicleStatusDTO;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.VehicleStatusRequests.CreateVehicleStatusRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.VehicleStatusRequests.UpdateVehicleStatusRequest;

import java.util.List;

public interface VehicleStatusService {
    void create(CreateVehicleStatusRequest createVehicleStatusRequest);

    VehicleStatusDTO update(UpdateVehicleStatusRequest updateVehicleStatusRequest);

    VehicleStatusDTO getById(int id);

    List<VehicleStatusDTO> getAll();

    VehicleStatusDTO getByStatus(String status);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);
}
