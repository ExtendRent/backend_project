package source_files.services.vehicleFeatures.abstracts;

import source_files.controllers.vehicle.dtos.CarModelDTO;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.CarModelRequests.CreateCarModelRequest;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.CarModelRequests.UpdateCarModelRequest;

import java.util.List;

public interface CarModelService {
    void create(CreateCarModelRequest createCarModelRequest);

    CarModelDTO update(UpdateCarModelRequest updateCarModelRequest);

    CarModelDTO getById(int id);

    CarModelDTO getByModelName(String modelName);

    List<CarModelDTO> getAll();

    List<CarModelDTO> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);

    //--------------------GETBYID METHODS-----------------------------------------------------------------
    List<CarModelDTO> getAllByBrandId(int brandId);
}
