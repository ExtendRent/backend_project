package src.repository.vehicle.features.car.model;

import src.controller.vehicle.features.car.model.requests.CreateCarModelRequest;
import src.controller.vehicle.features.car.model.requests.UpdateCarModelRequest;

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




