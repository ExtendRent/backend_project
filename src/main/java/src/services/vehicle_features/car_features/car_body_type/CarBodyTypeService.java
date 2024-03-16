package src.services.vehicle_features.car_features.car_body_type;

import src.controllers.vehicle.requests.vehicleFeatures.carBodyType.CreateCarBodyTypeRequest;
import src.controllers.vehicle.requests.vehicleFeatures.carBodyType.UpdateCarBodyTypeRequest;
import src.controllers.vehicle.responses.CarBodyTypeResponse;

import java.util.List;

public interface CarBodyTypeService {
    void create(CreateCarBodyTypeRequest createCarBodyTypeRequest);

    CarBodyTypeResponse update(UpdateCarBodyTypeRequest updateCarBodyTypeRequest);

    List<CarBodyTypeResponse> getAll();

    CarBodyTypeResponse getById(int id);

    void delete(int id, boolean hardDelete);

    List<CarBodyTypeResponse> getAllByDeletedState(boolean isDeleted);

    void softDelete(int id);
}
