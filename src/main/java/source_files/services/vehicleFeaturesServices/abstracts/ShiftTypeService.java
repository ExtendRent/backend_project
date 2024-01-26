package source_files.services.vehicleFeaturesServices.abstracts;

import source_files.data.DTO.itemDTOs.ShiftTypeDTO;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ShiftTypeRequests.CreateShiftTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ShiftTypeRequests.UpdateShiftTypeRequest;

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
