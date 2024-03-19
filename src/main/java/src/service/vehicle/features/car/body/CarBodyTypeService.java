package src.service.vehicle.features.car.body;

import src.controller.vehicle.features.car.body.request.CreateCarBodyTypeRequest;
import src.controller.vehicle.features.car.body.request.UpdateCarBodyTypeRequest;
import src.controller.vehicle.features.car.body.response.CarBodyTypeResponse;

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
