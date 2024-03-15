package source_files.services.vehicleFeatures.abstracts;

import source_files.controllers.vehicle.dtos.CarBodyTypeDTO;
import source_files.controllers.vehicle.requests.vehicleFeatures.carBodyType.CreateCarBodyTypeRequest;
import source_files.controllers.vehicle.requests.vehicleFeatures.carBodyType.UpdateCarBodyTypeRequest;

import java.util.List;

public interface CarBodyTypeService {
    void create(CreateCarBodyTypeRequest createCarBodyTypeRequest);

    CarBodyTypeDTO update(UpdateCarBodyTypeRequest updateCarBodyTypeRequest);

    List<CarBodyTypeDTO> getAll();

    CarBodyTypeDTO getById(int id);

    void delete(int id, boolean hardDelete);

    List<CarBodyTypeDTO> getAllByDeletedState(boolean isDeleted);

    void softDelete(int id);
}
