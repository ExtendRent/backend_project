package src.services.vehicle_features.car_features.car_body_type;

import src.controllers.vehicle.requests.vehicleFeatures.carBodyType.CreateCarBodyTypeRequest;
import src.controllers.vehicle.requests.vehicleFeatures.carBodyType.UpdateCarBodyTypeRequest;
import src.data.models.vehicle_entities.vehicle_features.car_features.CarBodyTypeEntity;

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
