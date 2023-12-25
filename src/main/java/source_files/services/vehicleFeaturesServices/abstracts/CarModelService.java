package source_files.services.vehicleFeaturesServices.abstracts;

import source_files.data.DTO.itemDTOs.CarModelDTO;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.CarModelRequests.AddCarModelRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.CarModelRequests.UpdateCarModelRequest;

import java.util.List;

public interface CarModelService {
    CarModelDTO add(AddCarModelRequest addCarModelRequest);

    CarModelDTO update(UpdateCarModelRequest updateCarModelRequest);

    CarModelDTO getById(int id);

    List<CarModelDTO> getAll();

    List<CarModelDTO> getAllByIsDeletedFalse();

    List<CarModelDTO> getAllByIsDeletedTrue();

    void delete(int id, boolean hardDelete);

    void hardDelete(int id);

    void softDelete(int id);

}
