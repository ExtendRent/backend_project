package source_files.services.entityServices.abstracts.vehicleAbstracts;

import source_files.data.models.vehicleEntities.CarEntity;
import source_files.data.requests.vehicleRequests.CarRequests.CreateCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;

import java.util.List;

public interface CarEntityService {

    CarEntity create(CreateCarRequest createCarRequest);

    CarEntity update(UpdateCarRequest updateCarRequest);

    CarEntity update(CarEntity carEntity);

    CarEntity getById(int id);

    List<CarEntity> getAll();

    List<CarEntity> getAllByDeletedState(boolean isDeleted);

    List<CarEntity> getAllByStatus(Integer statusId);

    List<CarEntity> getAllByColorId(int id);

    List<CarEntity> getAllByRentalPriceBetween(double startPrice, double endPrice);

    List<CarEntity> getAllByModelId(int id);

    List<CarEntity> getAllByYearBetween(int year1, int year2);

    List<CarEntity> getAllByBrandId(int id);

    List<CarEntity> getAllFiltered(
            Integer startPrice, Integer endPrice,
            Integer statusId, Integer colorId,
            Integer seat, Integer luggage, Integer modelId,
            Integer startYear, Integer endYear, Integer brandId,
            Integer fuelTypeId, Integer shiftTypeId, Integer segmentId);

    void delete(CarEntity carEntity);

    int getCountByDeletedState(boolean isDeleted);

    int getCountByStatusId(int statusId);
}
