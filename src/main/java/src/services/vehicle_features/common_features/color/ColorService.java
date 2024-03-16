package src.services.vehicle_features.common_features.color;

import src.controllers.vehicle.requests.vehicleFeatures.color.CreateColorRequest;
import src.controllers.vehicle.requests.vehicleFeatures.color.UpdateColorRequest;
import src.controllers.vehicle.responses.ColorResponse;

import java.util.List;

public interface ColorService {
    void create(CreateColorRequest createColorRequest);

    ColorResponse update(UpdateColorRequest updateColorRequest);

    ColorResponse getById(int id);

    List<ColorResponse> getAll();

    List<ColorResponse> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);

}
