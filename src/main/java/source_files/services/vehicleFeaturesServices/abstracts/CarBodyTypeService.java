package source_files.services.vehicleFeaturesServices.abstracts;

import source_files.data.DTO.itemDTOs.CarBodyTypeDTO;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.CreateCarBodyTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.UpdateCarBodyTypeRequest;

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
