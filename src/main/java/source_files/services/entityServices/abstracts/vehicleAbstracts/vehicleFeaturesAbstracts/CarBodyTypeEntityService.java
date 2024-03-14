package source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts;

import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.CarBodyTypeRequests.CreateCarBodyTypeRequest;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.CarBodyTypeRequests.UpdateCarBodyTypeRequest;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;

import java.util.List;

public interface CarBodyTypeEntityService {
    CarBodyTypeEntity create(CreateCarBodyTypeRequest createCarBodyTypeRequest);

    CarBodyTypeEntity update(UpdateCarBodyTypeRequest updateCarBodyTypeRequest);

    CarBodyTypeEntity update(CarBodyTypeEntity carBodyTypeEntity);

    CarBodyTypeEntity getById(int id);

    void delete(CarBodyTypeEntity carBodyTypeEntity);

    List<CarBodyTypeEntity> getAll();

    List<CarBodyTypeEntity> getAllByDeletedState(boolean isDeleted);
}
