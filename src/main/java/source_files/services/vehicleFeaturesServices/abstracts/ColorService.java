package source_files.services.vehicleFeaturesServices.abstracts;

import source_files.data.DTO.itemDTOs.ColorDTO;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.ColorRequests.AddColorRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.ColorRequests.UpdateColorRequest;

import java.util.List;

public interface ColorService {
    ColorDTO add(AddColorRequest addColorRequest);
    ColorDTO update(UpdateColorRequest updateColorRequest);
    ColorDTO getById(int id);
    void delete(int id);
    List<ColorDTO> getAll();

}
