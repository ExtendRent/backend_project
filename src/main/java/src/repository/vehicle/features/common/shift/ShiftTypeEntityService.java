package src.repository.vehicle.features.common.shift;

import src.controller.vehicle.features.common.shift.requests.CreateShiftTypeRequest;
import src.controller.vehicle.features.common.shift.requests.UpdateShiftTypeRequest;

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
