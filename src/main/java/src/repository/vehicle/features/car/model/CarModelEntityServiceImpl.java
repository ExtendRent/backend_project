package src.repository.vehicle.features.car.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.vehicle.features.car.model.requests.CreateCarModelRequest;
import src.controller.vehicle.features.car.model.requests.UpdateCarModelRequest;
import src.core.exception.DataNotFoundException;
import src.repository.vehicle.features.common.brand.BrandEntityService;

import java.util.List;

import static src.core.exception.type.NotFoundExceptionType.MODEL_DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CarModelEntityServiceImpl implements CarModelEntityService {

    private final CarModelRepository repository;
    private final BrandEntityService brandEntityService;

    @Override
    public CarModelEntity create(CreateCarModelRequest createCarModelRequest) {
        CarModelEntity carModelEntity = CarModelEntity.carModelBuilder()
                .name(createCarModelRequest.getCarModelEntityName())
                .brandEntity(brandEntityService.getById(createCarModelRequest.getBrandEntityId()))
                .build();
        return repository.save(carModelEntity);
    }

    @Override
    public CarModelEntity update(UpdateCarModelRequest updateCarModelRequest) {
        CarModelEntity carModelEntity = CarModelEntity.carModelBuilder()
                .id(updateCarModelRequest.getCarModelEntityId())
                .name(updateCarModelRequest.getCarModelEntityName())
                .brandEntity(brandEntityService.getById(updateCarModelRequest.getBrandEntityId()))
                .build();
        return repository.save(carModelEntity);
    }

    @Override
    public CarModelEntity update(CarModelEntity carModelEntity) {
        return repository.save(carModelEntity);
    }

    @Override
    public CarModelEntity getById(int id) {
        return repository.findById(id).orElseThrow(
                () -> new DataNotFoundException(MODEL_DATA_NOT_FOUND)
        );
    }

    @Override
    public CarModelEntity getByModelName(String modelName) {
        return repository.findByName(modelName)
                .orElseThrow(() -> new DataNotFoundException(MODEL_DATA_NOT_FOUND));
    }


    @Override
    public List<CarModelEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<CarModelEntity> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public List<CarModelEntity> getAllByBrandId(int id) {
        return repository.findAllByBrandEntity_Id(id);
    }

    @Override
    public void delete(CarModelEntity carModelEntity) {
        repository.delete(carModelEntity);
    }


}
