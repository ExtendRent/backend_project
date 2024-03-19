package src.service.vehicle.features.common.color;

import src.controller.vehicle.features.common.color.request.CreateColorRequest;
import src.controller.vehicle.features.common.color.request.UpdateColorRequest;
import src.controller.vehicle.features.common.color.response.ColorResponse;

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
