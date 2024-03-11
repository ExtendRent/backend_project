package source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.ShiftTypeEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ShiftTypeRequests.CreateShiftTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ShiftTypeRequests.UpdateShiftTypeRequest;

import java.util.List;

public interface ShiftTypeEntityService {
    ShiftTypeEntity create(CreateShiftTypeRequest createShiftTypeRequest);

    ShiftTypeEntity update(UpdateShiftTypeRequest updateShiftTypeRequest);

    ShiftTypeEntity update(ShiftTypeEntity shiftTypeEntity);

    ShiftTypeEntity getById(int id);

    List<ShiftTypeEntity> getAll();

    List<ShiftTypeEntity> getAllByDeletedState(boolean isDeleted);

    void delete(ShiftTypeEntity shiftTypeEntity);
}
