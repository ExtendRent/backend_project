package src.services.vehicle_features.common_features.fuel_type;

import src.controllers.vehicle.requests.vehicleFeatures.fuelType.CreateFuelTypeRequest;
import src.controllers.vehicle.requests.vehicleFeatures.fuelType.UpdateFuelTypeRequest;
import src.controllers.vehicle.responses.FuelTypeResponse;

import java.util.List;

public interface FuelTypeService {
    void create(CreateFuelTypeRequest createFuelTypeRequest);

    FuelTypeResponse update(UpdateFuelTypeRequest updateFuelTypeRequest);

    FuelTypeResponse getById(int id);

    List<FuelTypeResponse> getAll();

    List<FuelTypeResponse> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);
}
