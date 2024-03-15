package source_files.services.vehicle.abstracts;

import source_files.controllers.vehicle.dtos.CarDTO;
import source_files.controllers.vehicle.requests.car.CreateCarRequest;
import source_files.controllers.vehicle.requests.car.UpdateCarRequest;
import source_files.data.enums.defaultDataEnums.Status.DefaultVehicleStatus;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.models.vehicleEntities.CarEntity;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface CarService {

    void create(CreateCarRequest createCarRequest) throws IOException;

    CarDTO getById(int id);

    CarDTO update(UpdateCarRequest updateCarRequest) throws IOException;

    List<CarDTO> getAll();

    List<CarDTO> getAllFiltered(Integer customerID, Boolean licenseSuitable,
                                LocalDate startDate, LocalDate endDate,
                                Integer startPrice, Integer endPrice,
                                Boolean isDeleted, Integer statusId,
                                Integer colorId, Integer seat, Integer luggage, Integer modelId,
                                Integer startYear, Integer endYear,
                                Integer brandId, Integer fuelTypeID, Integer shiftTypeID, Integer segmentId);

    List<CarDTO> getAllByIsDrivingLicenseSuitable(Integer customerId);

    List<CarDTO> getAllByDeletedState(boolean isDeleted);

    List<CarDTO> getAllByStatus(Integer statusId);

    List<CarDTO> getAllByColorId(int id);

    List<CarDTO> getAllByModelId(int id);

    List<CarDTO> getAllByBrandId(int id);

    List<CarDTO> getAllByYearBetween(int startYear, int endYear);

    List<CarDTO> getAllByRentalPriceBetween(double startPrice, double endPrice);

    List<CarDTO> getAllByAvailabilityBetween(LocalDate startDate, LocalDate endDate);

    boolean isCarAvailableBetween(int carId, LocalDate startDate, LocalDate endDate);

    void delete(int id, boolean hardDelete) throws IOException;

    void softDelete(int id);

    void addRental(int carId, RentalEntity rentalEntity);

    void removeRental(int carId, RentalEntity rentalEntity);

    void changeStatus(CarEntity carEntity, DefaultVehicleStatus status);

    int getCountByDeletedState(boolean isDeleted);

    int getCountByStatusId(int statusId);
}
