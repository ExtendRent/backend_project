package src.services.vehicle_features.common_features.shift_type;

import src.controllers.vehicle.requests.vehicleFeatures.shiftType.CreateShiftTypeRequest;
import src.controllers.vehicle.requests.vehicleFeatures.shiftType.UpdateShiftTypeRequest;
import src.controllers.vehicle.responses.ShiftTypeResponse;

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
