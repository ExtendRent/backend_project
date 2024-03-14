package source_files.services.vehicleFeatures.abstracts;

import source_files.controllers.vehicle.dtos.ColorDTO;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.ColorRequests.CreateColorRequest;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.ColorRequests.UpdateColorRequest;

import java.util.List;

public interface ColorService {
    void create(CreateColorRequest createColorRequest);

    ColorDTO update(UpdateColorRequest updateColorRequest);

    ColorDTO getById(int id);

    List<ColorDTO> getAll();

    List<ColorDTO> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);

}
