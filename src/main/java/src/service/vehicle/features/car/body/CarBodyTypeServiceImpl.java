package src.service.vehicle.features.car.body;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.controller.vehicle.features.car.body.request.CreateCarBodyTypeRequest;
import src.controller.vehicle.features.car.body.request.UpdateCarBodyTypeRequest;
import src.controller.vehicle.features.car.body.response.CarBodyTypeResponse;
import src.repository.vehicle.features.car.body.CarBodyTypeEntity;
import src.repository.vehicle.features.car.body.CarBodyTypeEntityService;

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

    @Transactional
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
