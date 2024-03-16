package src.services.vehicle_features.common_features.color;

import src.controllers.vehicle.requests.vehicleFeatures.color.CreateColorRequest;
import src.controllers.vehicle.requests.vehicleFeatures.color.UpdateColorRequest;
import src.data.models.vehicle_entities.vehicle_features.ColorEntity;

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
