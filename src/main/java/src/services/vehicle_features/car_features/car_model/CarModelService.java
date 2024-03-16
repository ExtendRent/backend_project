package src.services.vehicle_features.car_features.car_model;

import src.controllers.vehicle.requests.vehicleFeatures.carModel.CreateCarModelRequest;
import src.controllers.vehicle.requests.vehicleFeatures.carModel.UpdateCarModelRequest;
import src.controllers.vehicle.responses.CarModelResponse;

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
