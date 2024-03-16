package src.services.vehicle_features.common_features.shift_type;

import src.controllers.vehicle.requests.vehicleFeatures.shiftType.CreateShiftTypeRequest;
import src.controllers.vehicle.requests.vehicleFeatures.shiftType.UpdateShiftTypeRequest;
import src.data.models.vehicle_entities.vehicle_features.ShiftTypeEntity;

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
