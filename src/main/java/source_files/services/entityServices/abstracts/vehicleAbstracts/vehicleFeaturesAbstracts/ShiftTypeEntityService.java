package source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.ShiftTypeEntity;

import java.util.List;

public interface ShiftTypeEntityService {
    ShiftTypeEntity create(ShiftTypeEntity shiftTypeEntity);

    ShiftTypeEntity update(ShiftTypeEntity shiftTypeEntity);

    ShiftTypeEntity getById(int id);

    List<ShiftTypeEntity> getAll();

    List<ShiftTypeEntity> getAllByDeletedState(boolean isDeleted);

    void delete(ShiftTypeEntity shiftTypeEntity);
}
