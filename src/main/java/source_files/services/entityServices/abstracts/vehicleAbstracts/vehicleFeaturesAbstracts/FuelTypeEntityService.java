package source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.FuelTypeEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.FuelTypeRequests.CreateFuelTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.FuelTypeRequests.UpdateFuelTypeRequest;

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
