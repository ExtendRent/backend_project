package source_files.services.vehicleFeatures.abstracts;

import source_files.controllers.vehicle.dtos.ShiftTypeDTO;
import source_files.controllers.vehicle.requests.vehicleFeatures.shiftType.CreateShiftTypeRequest;
import source_files.controllers.vehicle.requests.vehicleFeatures.shiftType.UpdateShiftTypeRequest;

import java.util.List;

public interface ShiftTypeService {
    void create(CreateShiftTypeRequest createShiftTypeRequest);

    ShiftTypeDTO update(UpdateShiftTypeRequest updateShiftTypeRequest);

    ShiftTypeDTO getById(int id);

    List<ShiftTypeDTO> getAll();

    List<ShiftTypeDTO> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);
}
