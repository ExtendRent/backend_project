package source_files.services.entityServices.vehicleEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.dataAccess.vehicleRepositories.CarRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.vehicleAbstracts.CarEntityService;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.CAR_DATA_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CarEntityManager implements CarEntityService {

    private final CarRepository carRepository;

    @Override
    public void create(CarEntity carEntity) {
        this.carRepository.save(carEntity);
    }

    @Override
    public CarEntity update(CarEntity carEntity) {
        return this.carRepository.save(carEntity);
    }

    @Override
    public CarEntity getById(int id) {
        return this.carRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(CAR_DATA_NOT_FOUND, "Araç Bulunamadı"));
    }

    @Override
    public List<CarEntity> getAll() {
        return this.carRepository.findAll();
    }

    @Override
    public List<CarEntity> getAllByDeletedState(boolean isDeleted) {
        return this.carRepository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public List<CarEntity> getAllByStatus(Integer statusId) {
        return this.carRepository.findAllByVehicleStatusEntityId(statusId);
    }

    @Override
    public List<CarEntity> getAllByColorId(int id) {
        return this.carRepository.findAllByColorEntity_Id(id);
    }

    @Override
    public List<CarEntity> getAllByRentalPriceBetween(double startPrice, double endPrice) {
        return this.carRepository.findAllByRentalPriceBetween(startPrice, endPrice);
    }

    @Override
    public List<CarEntity> getAllByAvailabilityBetween(LocalDate startDate, LocalDate endDate) {
        return this.carRepository.findAllByAvailabilityDateBetween(startDate, endDate);
    }

    @Override
    public List<CarEntity> getAllByModelId(int id) {
        return this.carRepository.findAllByCarModelEntity_Id(id);
    }

    @Override
    public List<CarEntity> getAllByYearBetween(int year1, int year2) {
        return this.carRepository.findAllByYearBetween(year1, year2);
    }

    @Override
    public List<CarEntity> getAllByBrandId(int brandId) {
        return this.carRepository.findAllByCarModelEntity_BrandEntity_Id(brandId);
    }

    @Override
    public List<CarEntity> getAllFiltered(
            Date startDate, Date endDate,
            Integer startPrice, Integer endPrice,
            Boolean isDeleted, Integer statusId,
            Integer colorId, Integer seat,
            Integer luggage, Integer modelId,
            Integer startYear, Integer endYear, Integer brandId,
            Integer fuelTypeId, Integer shiftTypeId) {

        return carRepository.findAllFiltered(
                startDate, endDate,
                startPrice, endPrice,
                isDeleted, statusId,
                colorId, seat, luggage,
                modelId, startYear,
                endYear, brandId, fuelTypeId, shiftTypeId);
    }

    @Override
    public void delete(CarEntity carEntity) {
        this.carRepository.delete(carEntity);
    }
}
