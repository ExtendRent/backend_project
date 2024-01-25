package source_files.services.vehicleService.abstracts;

import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.requests.vehicleRequests.CarRequests.CreateCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;

import java.time.LocalDate;
import java.util.List;

public interface CarService {

    void create(CreateCarRequest createCarRequest);

    CarDTO getById(int id);

    CarDTO update(UpdateCarRequest updateCarRequest);

    List<CarDTO> getAll();

    List<CarDTO> getAllWithLogin(int customerId);

    List<CarDTO> getAllFiltered(Integer customerID, LocalDate startDate, LocalDate endDate,
                                Integer startPrice, Integer endPrice,
                                Boolean isDeleted, Boolean isAvailable,
                                Integer colorId, Integer seat, Integer luggage, Integer modelId,
                                Integer startYear, Integer endYear, Integer brandId,
                                Integer fuelTypeID, Integer shiftTypeID);

    List<CarDTO> getAllByIsDrivingLicenseSuitable(Integer customerId);

    List<CarDTO> getAllByDeletedState(boolean isDeleted);

    List<CarDTO> getAllByAvailableState(boolean isAvailable);

    List<CarDTO> getAllByColorId(int id);

    List<CarDTO> getAllByModelId(int id);

    List<CarDTO> getAllByBrandId(int id);

    List<CarDTO> getAllByYearBetween(int startYear, int endYear);

    List<CarDTO> getAllByRentalPriceBetween(double startPrice, double endPrice);

    List<CarDTO> getAllByAvailabilityBetween(LocalDate startDate, LocalDate endDate);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);

}
