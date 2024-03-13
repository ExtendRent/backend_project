package source_files.services.vehicleFeaturesServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.itemDTOs.CarBodyTypeDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.CreateCarBodyTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.UpdateCarBodyTypeRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.CarBodyTypeRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarBodyTypeEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.CarBodyTypeService;

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
    public CarBodyTypeDTO update(UpdateCarBodyTypeRequest updateCarBodyTypeRequest) {
        updateCarBodyTypeRequest = rules.fix(updateCarBodyTypeRequest);
        rules.check(updateCarBodyTypeRequest);
        return entityService.update(updateCarBodyTypeRequest).toModel();
    }

    @Override
    public List<CarBodyTypeDTO> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public CarBodyTypeDTO getById(int id) {
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
    public List<CarBodyTypeDTO> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
    }


    @Override
    public void softDelete(int id) {
        CarBodyTypeEntity carBodyTypeEntity = entityService.getById(id);
        carBodyTypeEntity.setIsDeleted(true);
        carBodyTypeEntity.setDeletedAt(LocalDateTime.now());
        entityService.update(carBodyTypeEntity);
    }

    private List<CarBodyTypeDTO> mapToDTOList(List<CarBodyTypeEntity> carBodyTypeEntities) {
        rules.checkDataList(carBodyTypeEntities);
        return carBodyTypeEntities.stream().map(CarBodyTypeEntity::toModel).toList();
    }
}
