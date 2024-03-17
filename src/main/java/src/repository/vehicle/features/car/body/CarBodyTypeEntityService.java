package src.repository.vehicle.features.car.body;

import src.controller.vehicle.features.car.body.requests.CreateCarBodyTypeRequest;
import src.controller.vehicle.features.car.body.requests.UpdateCarBodyTypeRequest;

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
