package src.services.vehicle_features.common_features.fuel_type;

import src.controllers.vehicle.requests.vehicleFeatures.fuelType.CreateFuelTypeRequest;
import src.controllers.vehicle.requests.vehicleFeatures.fuelType.UpdateFuelTypeRequest;
import src.data.models.vehicle_entities.vehicle_features.FuelTypeEntity;

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
