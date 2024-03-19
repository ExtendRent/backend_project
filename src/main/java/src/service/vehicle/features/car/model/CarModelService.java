package src.service.vehicle.features.car.model;

import src.controller.vehicle.features.car.model.request.CreateCarModelRequest;
import src.controller.vehicle.features.car.model.request.UpdateCarModelRequest;
import src.controller.vehicle.features.car.model.response.CarModelResponse;

import java.util.List;

public interface CarModelService {
    void create(CreateCarModelRequest createCarModelRequest);

    CarModelResponse update(UpdateCarModelRequest updateCarModelRequest);

    CarModelResponse getById(int id);

    CarModelResponse getByModelName(String modelName);

    List<CarModelResponse> getAll();

    List<CarModelResponse> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);

    //--------------------GETBYID METHODS-----------------------------------------------------------------
    List<CarModelResponse> getAllByBrandId(int brandId);
}
