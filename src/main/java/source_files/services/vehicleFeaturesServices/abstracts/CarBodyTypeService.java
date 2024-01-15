package source_files.services.vehicleFeaturesServices.abstracts;

import source_files.data.DTO.itemDTOs.CarBodyTypeDTO;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.AddCarBodyTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.UpdateCarBodyTypeRequest;

import java.util.List;

public interface CarBodyTypeService {
    CarBodyTypeDTO add(AddCarBodyTypeRequest addCarBodyTypeRequest);

    CarBodyTypeDTO update(UpdateCarBodyTypeRequest updateCarBodyTypeRequest);

    List<CarBodyTypeDTO> getAll();

    CarBodyTypeDTO getById(int id);

    void delete(int id, boolean hardDelete);

    List<CarBodyTypeDTO> getAllByDeletedState(boolean isDeleted);

    void hardDelete(int id);

    void softDelete(int id);
}
