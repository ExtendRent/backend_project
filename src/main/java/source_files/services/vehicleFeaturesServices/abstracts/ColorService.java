package source_files.services.vehicleFeaturesServices.abstracts;

import source_files.data.DTO.itemDTOs.ColorDTO;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.CreateColorRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.UpdateColorRequest;

import java.util.List;

public interface ColorService {
    void create(CreateColorRequest createColorRequest);

    ColorDTO update(UpdateColorRequest updateColorRequest);

    ColorDTO getById(int id);

    List<ColorDTO> getAll() throws Exception;

    List<ColorDTO> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);

}
