package src.repository.vehicle.features.common.color;

import src.controller.vehicle.features.common.color.requests.CreateColorRequest;
import src.controller.vehicle.features.common.color.requests.UpdateColorRequest;

import java.util.List;

public interface ColorEntityService {
    ColorEntity create(CreateColorRequest createColorRequest);

    ColorEntity update(UpdateColorRequest updateColorRequest);

    ColorEntity update(ColorEntity colorEntity);

    ColorEntity getById(int id);

    List<ColorEntity> getAll();

    List<ColorEntity> getAllByDeletedState(boolean isDeleted);

    void delete(ColorEntity colorEntity);

}
