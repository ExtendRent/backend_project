package source_files.services.vehicleFeaturesServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.CarBodyTypeDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.AddCarBodyTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.UpdateCarBodyTypeRequest;
import source_files.data.types.ItemType;
import source_files.dataAccess.vehicleFeaturesRespositories.CarBodyTypeRepository;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.CarBodyTypeBusinessRules;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.CarBodyTypeEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.CarBodyTypeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarBodyTypeManager implements CarBodyTypeService {

    private final CarBodyTypeEntityService carBodyTypeEntityService;
    private final CarBodyTypeBusinessRules carBodyTypeBusinessRules;
    private final ModelMapperService modelMapperService;
    private final CarBodyTypeRepository carBodyTypeRepository;

    @Override
    public CarBodyTypeDTO add(AddCarBodyTypeRequest addCarBodyTypeRequest) {
        CarBodyTypeEntity carBodyTypeEntity = modelMapperService.forRequest().
                map(carBodyTypeBusinessRules.checkAddCarBodyTypeRequest
                        (carBodyTypeBusinessRules.fixAddCarBodyTypeRequest(addCarBodyTypeRequest)), CarBodyTypeEntity.class);
        carBodyTypeEntity.setItemType(ItemType.CAR_BODY_TYPE);
        return this.modelMapperService.forResponse().map(this.carBodyTypeEntityService.add(carBodyTypeEntity), CarBodyTypeDTO.class);
    }

    @Override
    public CarBodyTypeDTO update(UpdateCarBodyTypeRequest updateCarBodyTypeRequest) {
        CarBodyTypeEntity carBodyTypeEntity = modelMapperService.forRequest()
                .map(carBodyTypeBusinessRules.checkUpdateCarBodyTypeRequest(
                        carBodyTypeBusinessRules.fixUpdateCarBodyTypeRequest(updateCarBodyTypeRequest)), CarBodyTypeEntity.class);
        carBodyTypeEntity.setItemType(ItemType.CAR_BODY_TYPE);
        return this.modelMapperService.forResponse().map(this.carBodyTypeEntityService.update(carBodyTypeEntity), CarBodyTypeDTO.class);
    }

    @Override
    public List<CarBodyTypeDTO> getAll() {
        return this.carBodyTypeBusinessRules.checkDataList(carBodyTypeEntityService.getAll())
                .stream().map(carBodyType -> modelMapperService.forResponse()
                        .map(carBodyType, CarBodyTypeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public CarBodyTypeDTO getById(int id) {
        return this.modelMapperService.forResponse().map(carBodyTypeEntityService.getById(id), CarBodyTypeDTO.class);
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete)
            this.hardDelete(id);
        else
            this.softDelete(id);
    }

    @Override
    public List<CarBodyTypeDTO> getAllByIsDeletedFalse() {
        return carBodyTypeBusinessRules.checkDataList(this.carBodyTypeRepository.findAllByIsDeletedFalse())
                .stream().map(carBodyTypeEntity -> modelMapperService.forResponse()
                        .map(carBodyTypeEntity, CarBodyTypeDTO.class)).toList();
    }

    @Override
    public List<CarBodyTypeDTO> getAllByIsDeletedTrue() {
        return carBodyTypeBusinessRules.checkDataList(this.carBodyTypeRepository.findAllByIsDeletedTrue())
                .stream().map(carBodyTypeEntity -> modelMapperService.forResponse()
                        .map(carBodyTypeEntity, CarBodyTypeDTO.class)).toList();
    }

    @Override
    public void hardDelete(int id) {
        this.carBodyTypeEntityService.delete(this.carBodyTypeEntityService.getById(id));
    }

    @Override
    public void softDelete(int id) {
        CarBodyTypeEntity carBodyTypeEntity = this.carBodyTypeEntityService.getById(id);
        carBodyTypeEntity.setIsDeleted(true);
        this.carBodyTypeEntityService.update(carBodyTypeEntity);
    }
}
