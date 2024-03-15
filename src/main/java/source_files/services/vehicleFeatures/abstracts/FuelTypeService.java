package source_files.services.vehicleFeatures.abstracts;

import source_files.controllers.vehicle.dtos.FuelTypeDTO;
import source_files.controllers.vehicle.requests.vehicleFeatures.fuelType.CreateFuelTypeRequest;
import source_files.controllers.vehicle.requests.vehicleFeatures.fuelType.UpdateFuelTypeRequest;

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
