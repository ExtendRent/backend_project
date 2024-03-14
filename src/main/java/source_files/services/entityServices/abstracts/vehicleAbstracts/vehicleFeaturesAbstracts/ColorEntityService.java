package source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts;

import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.ColorRequests.CreateColorRequest;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.ColorRequests.UpdateColorRequest;
import source_files.data.models.vehicleEntities.vehicleFeatures.ColorEntity;

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
