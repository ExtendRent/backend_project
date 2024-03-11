package source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.CreateCarModelRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.UpdateCarModelRequest;

import java.util.List;

public interface CarModelEntityService {
    CarModelEntity create(CreateCarModelRequest createCarModelRequest);

    CarModelEntity update(UpdateCarModelRequest updateCarModelRequest);

    CarModelEntity update(CarModelEntity carModelEntity);

    CarModelEntity getById(int id);

    CarModelEntity getByModelName(String modelName);

    List<CarModelEntity> getAll();

    List<CarModelEntity> getAllByDeletedState(boolean isDeleted);

    List<CarModelEntity> getAllByBrandId(int id);

    void delete(CarModelEntity carModelEntity);
}




