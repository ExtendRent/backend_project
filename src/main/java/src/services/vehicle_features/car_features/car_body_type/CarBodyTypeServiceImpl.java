package src.services.vehicle_features.car_features.car_body_type;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.vehicle.requests.vehicleFeatures.carBodyType.CreateCarBodyTypeRequest;
import src.controllers.vehicle.requests.vehicleFeatures.carBodyType.UpdateCarBodyTypeRequest;
import src.controllers.vehicle.responses.CarBodyTypeResponse;
import src.data.models.vehicle_entities.vehicle_features.car_features.CarBodyTypeEntity;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarBodyTypeServiceImpl implements CarBodyTypeService {

    private final CarBodyTypeEntityService entityService;
    private final CarBodyTypeRules rules;

    @Override
    public void create(CreateCarBodyTypeRequest createCarBodyTypeRequest) {
        createCarBodyTypeRequest = rules.fix(createCarBodyTypeRequest);
        rules.check(createCarBodyTypeRequest);
        entityService.create(createCarBodyTypeRequest);
    }

    @Override
    public CarBodyTypeResponse update(UpdateCarBodyTypeRequest updateCarBodyTypeRequest) {
        updateCarBodyTypeRequest = rules.fix(updateCarBodyTypeRequest);
        rules.check(updateCarBodyTypeRequest);
        return entityService.update(updateCarBodyTypeRequest).toModel();
    }

    @Override
    public List<CarBodyTypeResponse> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public CarBodyTypeResponse getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete)
            entityService.delete(entityService.getById(id));
        else
            softDelete(id);
    }

    @Override
    public List<CarBodyTypeResponse> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
    }


    @Override
    public void softDelete(int id) {
        CarBodyTypeEntity carBodyTypeEntity = entityService.getById(id);
        carBodyTypeEntity.setIsDeleted(true);
        carBodyTypeEntity.setDeletedAt(LocalDateTime.now());
        entityService.update(carBodyTypeEntity);
    }

    private List<CarBodyTypeResponse> mapToDTOList(List<CarBodyTypeEntity> carBodyTypeEntities) {
        rules.checkDataList(carBodyTypeEntities);
        return carBodyTypeEntities.stream().map(CarBodyTypeEntity::toModel).toList();
    }
}
