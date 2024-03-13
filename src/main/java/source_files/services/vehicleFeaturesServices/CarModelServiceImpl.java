package source_files.services.vehicleFeaturesServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.itemDTOs.CarModelDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.CreateCarModelRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.UpdateCarModelRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.CarModelRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarModelEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.CarModelService;

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
    public CarModelDTO update(UpdateCarModelRequest updateCarModelRequest) {
        updateCarModelRequest = rules.fix(updateCarModelRequest);
        rules.check(updateCarModelRequest);
        return entityService.update(updateCarModelRequest).toModel();
    }

    @Override
    public CarModelDTO getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public CarModelDTO getByModelName(String modelName) {
        return entityService.getByModelName(modelName).toModel();
    }

    @Override
    public List<CarModelDTO> getAll() {
        return mapToDTOList(entityService.getAll());
    }


    @Override
    public List<CarModelDTO> getAllByDeletedState(boolean isDeleted) {
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
    public List<CarModelDTO> getAllByBrandId(int brandId) {
        return mapToDTOList(entityService.getAllByBrandId(brandId));
    }

    private List<CarModelDTO> mapToDTOList(List<CarModelEntity> carModelEntities) {
        rules.checkDataList(carModelEntities);
        return carModelEntities.stream().map(CarModelEntity::toModel).toList();
    }

}
