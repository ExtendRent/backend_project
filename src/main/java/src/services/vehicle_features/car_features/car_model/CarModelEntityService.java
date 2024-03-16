package src.services.vehicle_features.car_features.car_model;

import src.controllers.vehicle.requests.vehicleFeatures.carModel.CreateCarModelRequest;
import src.controllers.vehicle.requests.vehicleFeatures.carModel.UpdateCarModelRequest;
import src.data.models.vehicle_entities.vehicle_features.car_features.CarModelEntity;

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




