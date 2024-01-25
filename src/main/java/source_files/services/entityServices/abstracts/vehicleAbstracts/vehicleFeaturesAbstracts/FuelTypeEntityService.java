package source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.FuelTypeEntity;

import java.util.List;

public interface FuelTypeEntityService {
    FuelTypeEntity create(FuelTypeEntity fuelTypeEntity);

    FuelTypeEntity update(FuelTypeEntity fuelTypeEntity);

    FuelTypeEntity getById(int id);

    List<FuelTypeEntity> getAll();

    List<FuelTypeEntity> getAllByDeletedState(boolean isDeleted);

    void delete(FuelTypeEntity fuelTypeEntity);
}
