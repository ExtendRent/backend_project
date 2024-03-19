package src.service.vehicle.features.car.model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.controller.vehicle.features.car.model.request.CreateCarModelRequest;
import src.controller.vehicle.features.car.model.request.UpdateCarModelRequest;
import src.controller.vehicle.features.car.model.response.CarModelResponse;
import src.repository.vehicle.features.car.model.CarModelEntity;
import src.repository.vehicle.features.car.model.CarModelEntityService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarModelServiceImpl implements CarModelService {

    private final CarModelEntityService entityService;
    private final CarModelRules rules;

    @Override
    public void create(CreateCarModelRequest createCarModelRequest) {
        createCarModelRequest = rules.fix(createCarModelRequest);
        rules.check(createCarModelRequest);
        entityService.create(createCarModelRequest);
    }

    @Override
    public CarModelResponse update(UpdateCarModelRequest updateCarModelRequest) {
        updateCarModelRequest = rules.fix(updateCarModelRequest);
        rules.check(updateCarModelRequest);
        return entityService.update(updateCarModelRequest).toModel();
    }

    @Override
    public CarModelResponse getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public CarModelResponse getByModelName(String modelName) {
        return entityService.getByModelName(modelName).toModel();
    }

    @Override
    public List<CarModelResponse> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Transactional
    @Override
    public List<CarModelResponse> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
    }

    @Override
    public void delete(int id, boolean hardDelete) {

        if (hardDelete) {
            entityService.delete(entityService.getById(id));
        } else {
            softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        CarModelEntity carModelEntity = entityService.getById(id);
        carModelEntity.setIsDeleted(true);
        carModelEntity.setDeletedAt(LocalDateTime.now());
        entityService.update(carModelEntity);
    }

    @Override
    public List<CarModelResponse> getAllByBrandId(int brandId) {
        return mapToDTOList(entityService.getAllByBrandId(brandId));
    }

    private List<CarModelResponse> mapToDTOList(List<CarModelEntity> carModelEntities) {
        rules.checkDataList(carModelEntities);
        return carModelEntities.stream().map(CarModelEntity::toModel).toList();
    }

}
