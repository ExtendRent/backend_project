package src.repository.vehicle.features.common.fuel;

import src.controller.vehicle.features.common.fuel.requests.CreateFuelTypeRequest;
import src.controller.vehicle.features.common.fuel.requests.UpdateFuelTypeRequest;

import java.util.List;

public interface FuelTypeEntityService {
    FuelTypeEntity create(CreateFuelTypeRequest createFuelTypeRequest);

    FuelTypeEntity update(UpdateFuelTypeRequest updateFuelTypeRequest);

    FuelTypeEntity update(FuelTypeEntity fuelTypeEntity);

    FuelTypeEntity getById(int id);

    List<FuelTypeEntity> getAll();

    List<FuelTypeEntity> getAllByDeletedState(boolean isDeleted);

    void delete(FuelTypeEntity fuelTypeEntity);
}
