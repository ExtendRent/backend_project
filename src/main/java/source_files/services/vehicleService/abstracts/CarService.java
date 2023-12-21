package source_files.services.vehicleService.abstracts;

import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.requests.vehicleRequests.CarRequests.AddCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;

import java.util.List;

public interface CarService {

    CarDTO add(AddCarRequest addCarRequest);

    CarDTO getById(int id);

    CarDTO update(UpdateCarRequest updateCarRequest);

    List<CarDTO> getAll() throws Exception;

    List<CarDTO> getAllByIsDeletedFalse();

    List<CarDTO> getAllByIsDeletedTrue();

    void delete(int id, boolean hardDelete);

    void hardDelete(int id);

    void softDelete(int id);

}
