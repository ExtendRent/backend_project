package src.service.vehicle.features.common.fuel;

import src.controller.vehicle.features.common.fuel.request.CreateFuelTypeRequest;
import src.controller.vehicle.features.common.fuel.request.UpdateFuelTypeRequest;
import src.controller.vehicle.features.common.fuel.response.FuelTypeResponse;

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
