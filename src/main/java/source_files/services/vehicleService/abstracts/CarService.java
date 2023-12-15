package source_files.services.vehicleService.abstracts;

import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.requests.vehicleRequests.CarRequests.AddCarRequest;

public interface CarService {

    CarDTO add(AddCarRequest addCarRequest);

    CarDTO getById(int id);

}
