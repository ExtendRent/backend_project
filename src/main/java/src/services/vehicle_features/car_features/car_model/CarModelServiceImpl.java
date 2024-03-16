package src.services.vehicle_features.car_features.car_model;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.vehicle.requests.vehicleFeatures.carModel.CreateCarModelRequest;
import src.controllers.vehicle.requests.vehicleFeatures.carModel.UpdateCarModelRequest;
import src.controllers.vehicle.responses.CarModelResponse;
import src.data.models.vehicle_entities.vehicle_features.car_features.CarModelEntity;

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
