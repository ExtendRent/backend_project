package source_files.services.entityServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.dataAccess.vehicleRepositories.CarRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.CarEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.CAR_DATA_NOT_FOUND;

@AllArgsConstructor
@Service
public class CarEntityManager implements CarEntityService {

    private final CarRepository carRepository;

    @Override
    public void add(CarEntity carEntity) {
        this.carRepository.save(carEntity);
    }

    @Override
    public CarEntity update(CarEntity carEntity) {
        //CarEntity uptatedCar = carRepository.findById(id).orElseThrow();
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
    public List<CarEntity> getAllByAvailability(boolean isAvailable) {
        return this.carRepository.findAllByIsAvailable(isAvailable);
    }

    @Override
    public List<CarEntity> getAllByColorId(int id) {
        return this.carRepository.findAllByColorEntity_Id(id);
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
    public List<CarEntity> getAllByBrandId(int id) {
        return this.carRepository.findAllByCarModelEntity_BrandEntity_Id(id);
    }

    @Override
    public void delete(CarEntity carEntity) {
        this.carRepository.delete(carEntity);
    }
}
