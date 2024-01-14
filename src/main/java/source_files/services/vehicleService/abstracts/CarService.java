package source_files.services.vehicleService.abstracts;

import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.requests.vehicleRequests.CarRequests.AddCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;

import java.util.List;

public interface CarService {

    void add(AddCarRequest addCarRequest);

    CarDTO getById(int id);

    CarDTO update(UpdateCarRequest updateCarRequest);

    List<CarDTO> getAll() throws Exception;

    List<CarDTO> getAllByDeletedState(boolean isDeleted);

    List<CarDTO> getAllByAvailableState(boolean isAvailable);

    List<CarDTO> getAllByColorId(int id);

    List<CarDTO> getAllByModelId(int id);

    List<CarDTO> getAllByBrandId(int id);

    List<CarDTO> getAllByYearBetween(int year1, int year2);

    void delete(int id, boolean hardDelete);

    void hardDelete(int id);

    void softDelete(int id);

}
