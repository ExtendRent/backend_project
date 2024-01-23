package source_files.services.vehicleFeaturesServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.CarBodyTypeDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.CreateCarBodyTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.UpdateCarBodyTypeRequest;
import source_files.data.types.itemTypes.ItemType;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.CarBodyTypeBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarBodyTypeEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.CarBodyTypeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarBodyTypeManager implements CarBodyTypeService {

    private final CarBodyTypeEntityService carBodyTypeEntityService;
    private final CarBodyTypeBusinessRules carBodyTypeBusinessRules;
    private final ModelMapperService modelMapperService;

    @Override
    public void create(CreateCarBodyTypeRequest createCarBodyTypeRequest) {
        CarBodyTypeEntity carBodyTypeEntity = modelMapperService.forRequest().
                map(carBodyTypeBusinessRules.checkCreateCarBodyTypeRequest
                        (carBodyTypeBusinessRules.fixCreateCarBodyTypeRequest(createCarBodyTypeRequest)), CarBodyTypeEntity.class);
        carBodyTypeEntity.setItemType(ItemType.CAR_BODY_TYPE);
        this.carBodyTypeEntityService.create(carBodyTypeEntity);
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
            this.carBodyTypeEntityService.delete(this.carBodyTypeEntityService.getById(id));
        else
            this.softDelete(id);
    }

    @Override
    public List<CarBodyTypeDTO> getAllByDeletedState(boolean isDeleted) {
        return this.carBodyTypeBusinessRules.checkDataList(carBodyTypeEntityService.getAllByDeletedState(isDeleted))
                .stream().map(carBodyType -> modelMapperService.forResponse()
                        .map(carBodyType, CarBodyTypeDTO.class)).collect(Collectors.toList());
    }


    @Override
    public void softDelete(int id) {
        CarBodyTypeEntity carBodyTypeEntity = this.carBodyTypeEntityService.getById(id);
        carBodyTypeEntity.setIsDeleted(true);
        this.carBodyTypeEntityService.update(carBodyTypeEntity);
    }
}
