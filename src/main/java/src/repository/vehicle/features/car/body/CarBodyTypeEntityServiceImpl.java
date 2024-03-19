package src.repository.vehicle.features.car.body;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.vehicle.features.car.body.request.CreateCarBodyTypeRequest;
import src.controller.vehicle.features.car.body.request.UpdateCarBodyTypeRequest;
import src.core.exception.DataNotFoundException;

import java.util.List;

import static src.core.exception.type.NotFoundExceptionType.BODY_TYPE_DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CarBodyTypeEntityServiceImpl implements CarBodyTypeEntityService {

    private final CarBodyTypeRepository repository;


    @Override
    public CarBodyTypeEntity create(CreateCarBodyTypeRequest createCarBodyTypeRequest) {
        CarBodyTypeEntity carBodyTypeEntity = CarBodyTypeEntity.carBodyTypeBuilder()
                .name(createCarBodyTypeRequest.getCarBodyTypeEntityName())
                .build();
        return this.repository.save(carBodyTypeEntity);
    }

    @Override
    public CarBodyTypeEntity update(UpdateCarBodyTypeRequest updateCarBodyTypeRequest) {
        CarBodyTypeEntity carBodyTypeEntity = CarBodyTypeEntity.carBodyTypeBuilder()
                .id(updateCarBodyTypeRequest.getId())
                .name(updateCarBodyTypeRequest.getName())
                .build();
        return repository.save(carBodyTypeEntity);
    }

    @Override
    public CarBodyTypeEntity update(CarBodyTypeEntity carBodyTypeEntity) {
        return repository.save(carBodyTypeEntity);
    }

    @Override
    public CarBodyTypeEntity getById(int id) {
        return repository.findById(id).orElseThrow(
                () -> new DataNotFoundException(BODY_TYPE_DATA_NOT_FOUND)
        );
    }

    @Override
    public void delete(CarBodyTypeEntity carBodyTypeEntity) {
        repository.delete(carBodyTypeEntity);
    }

    @Override
    public List<CarBodyTypeEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<CarBodyTypeEntity> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted);
    }


}


