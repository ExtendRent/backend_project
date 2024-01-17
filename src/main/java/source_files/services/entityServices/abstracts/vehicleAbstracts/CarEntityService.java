package source_files.services.entityServices.abstracts.vehicleAbstracts;

import source_files.data.models.vehicleEntities.CarEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface CarEntityService {

    void add(CarEntity carEntity);

    CarEntity update(CarEntity carEntity);

    CarEntity getById(int id);

    List<CarEntity> getAll();

    List<CarEntity> getAllByDeletedState(boolean isDeleted);

    List<CarEntity> getAllByAvailability(boolean isAvailable);

    List<CarEntity> getAllByColorId(int id);

    List<CarEntity> getAllByRentalPriceBetween(double startPrice, double endPrice);

    List<CarEntity> getAllByAvailabilityBetween(LocalDate startDate, LocalDate endDate);

    List<CarEntity> getAllByModelId(int id);

    List<CarEntity> getAllByYearBetween(int year1, int year2);

    List<CarEntity> getAllByBrandId(int id);

    List<CarEntity> getAllFiltered(
            Date startDate, Date endDate,
            Integer startPrice, Integer endPrice,
            Boolean isDeleted, Boolean isAvailable,
            Integer colorId, String fuelType, String shiftType,
            Integer seat, Integer luggage, Integer modelId,
            Integer startYear, Integer endYear, Integer brandId);

    void delete(CarEntity carEntity);
}
