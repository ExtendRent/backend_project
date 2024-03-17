package src.service.vehicle.features.common.shift;

import src.controller.vehicle.features.common.shift.requests.CreateShiftTypeRequest;
import src.controller.vehicle.features.common.shift.requests.UpdateShiftTypeRequest;
import src.controller.vehicle.features.common.shift.responses.ShiftTypeResponse;

import java.util.List;

public interface ShiftTypeService {
    void create(CreateShiftTypeRequest createShiftTypeRequest);

    ShiftTypeResponse update(UpdateShiftTypeRequest updateShiftTypeRequest);

    ShiftTypeResponse getById(int id);

    List<ShiftTypeResponse> getAll();

    List<ShiftTypeResponse> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);
}
