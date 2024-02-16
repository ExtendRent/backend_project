package source_files.services.entityServices.vehicleEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.dataAccess.vehicleRepositories.CarRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.vehicleAbstracts.CarEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.CAR_DATA_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CarEntityManager implements CarEntityService {

    private final CarRepository repository;


    @Override
    public CarEntity create(CarEntity carEntity) {
        return repository.save(carEntity);
    }

    @Override
    public CarEntity update(CarEntity carEntity) {
        return repository.save(carEntity);
    }

    @Override
    public CarEntity getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(CAR_DATA_NOT_FOUND, "Araç Bulunamadı"));
    }

    @Override
    public List<CarEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<CarEntity> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public List<CarEntity> getAllByStatus(Integer statusId) {
        return repository.findAllByVehicleStatusEntityId(statusId);
    }

    @Override
    public List<CarEntity> getAllByColorId(int id) {
        return repository.findAllByColorEntity_Id(id);
    }

    @Override
    public List<CarEntity> getAllByRentalPriceBetween(double startPrice, double endPrice) {
        return repository.findAllByRentalPriceBetween(startPrice, endPrice);
    }


    @Override
    public List<CarEntity> getAllByModelId(int id) {
        return repository.findAllByCarModelEntity_Id(id);
    }

    @Override
    public List<CarEntity> getAllByYearBetween(int year1, int year2) {
        return repository.findAllByYearBetween(year1, year2);
    }

    @Override
    public List<CarEntity> getAllByBrandId(int brandId) {
        return repository.findAllByCarModelEntity_BrandEntity_Id(brandId);
    }

    @Override
    public List<CarEntity> getAllFiltered(
            Integer startPrice, Integer endPrice,
            Integer statusId,
            Integer colorId, Integer seat,
            Integer luggage, Integer modelId,
            Integer startYear, Integer endYear, Integer brandId,
            Integer fuelTypeId, Integer shiftTypeId, Integer segmentId) {

        return repository.findAllFiltered(
                startPrice, endPrice,
                statusId, colorId,
                seat, luggage,
                modelId, startYear,
                endYear, brandId, fuelTypeId,
                shiftTypeId, segmentId);
    }

    @Override
    public void delete(CarEntity carEntity) {
        repository.delete(carEntity);
    }
}
