package source_files.services.vehicleFeaturesServices.abstracts;

import source_files.data.DTO.itemDTOs.FuelTypeDTO;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.FuelTypeRequests.CreateFuelTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.FuelTypeRequests.UpdateFuelTypeRequest;

import java.util.List;

public interface FuelTypeService {
    void create(CreateFuelTypeRequest createFuelTypeRequest);

    FuelTypeDTO update(UpdateFuelTypeRequest updateFuelTypeRequest);

    FuelTypeDTO getById(int id);

    List<FuelTypeDTO> getAll();

    List<FuelTypeDTO> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);
}
