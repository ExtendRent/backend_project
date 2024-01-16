package source_files.services.vehicleFeaturesServices.abstracts;

import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.DTO.itemDTOs.CarModelDTO;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.AddCarModelRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.UpdateCarModelRequest;

import java.util.List;

public interface CarModelService {
    CarModelDTO add(AddCarModelRequest addCarModelRequest);

    CarModelDTO update(UpdateCarModelRequest updateCarModelRequest);

    CarModelDTO getById(int id);

    CarModelDTO getByModelName(String modelName);

    List<CarModelDTO> getAll();

    List<CarModelDTO> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);

    //--------------------GETBYID METHODS-----------------------------------------------------------------
    List<BrandDTO> getAllByBrandId(int brandId);
}
