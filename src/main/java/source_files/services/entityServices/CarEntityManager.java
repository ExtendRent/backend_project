package source_files.services.entityServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.dataAccess.vehicleRepositories.CarRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.CarEntityService;

import java.util.List;

import static source_files.exception.NotFoundExceptionType.CAR_DATA_NOT_FOUND;

@AllArgsConstructor
@Service
public class CarEntityManager implements CarEntityService {

    private final CarRepository carRepository;

    @Override
    public CarEntity add(CarEntity carEntity) {
        return this.carRepository.save(carEntity);
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
    public List<CarEntity> getAllByIsDeletedFalse() {
        return this.carRepository.findAllByIsDeletedFalse();
    }

    @Override
    public List<CarEntity> getAllByIsDeletedTrue() {
        return this.carRepository.findAllByIsDeletedTrue();
    }

    @Override
    public List<CarEntity> getAllByIsAvailableTrue() {
        return this.carRepository.findAllByIsAvailableTrue();
    }

    @Override
    public List<CarEntity> getAllByIsAvailableFalse() {
        return this.carRepository.findAllByIsAvailableFalse();
    }

    @Override
    public List<CarEntity> getAllByColorId(int id) {
        return this.carRepository.findAllByColorId(id);
    }

    @Override
    public List<CarEntity> getAllByModelId(int id) {
        return this.carRepository.findAllByModelId(id);
    }

    @Override
    public List<CarEntity> getAllByYearBetween(int year1, int year2) {
        return this.carRepository.findAllByYearBetween(year1, year2);
    }

    @Override
    public void delete(CarEntity carEntity) {
        this.carRepository.delete(carEntity);
    }
}
