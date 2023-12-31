package source_files.services.vehicleFeaturesServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.DTO.itemDTOs.CarModelDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.CarModelRequests.AddCarModelRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.CarModelRequests.UpdateCarModelRequest;
import source_files.services.BusinessRules.CarModelBusinessRules;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.CarModelEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.CarModelService;

import java.util.List;
import java.util.stream.Collectors;

import static source_files.data.types.ItemType.CAR_MODEL;

@Service
@AllArgsConstructor
public class CarModelManager implements CarModelService {

    private final CarModelEntityService carModelEntityService;
    private final ModelMapperService modelMapperService;
    private final CarModelBusinessRules carModelBusinessRules;

    @Override
    public CarModelDTO add(AddCarModelRequest addCarModelRequest) {

        CarModelEntity carModelEntity = modelMapperService.forRequest()
                .map(carModelBusinessRules.checkAddCarModelRequest(
                        carModelBusinessRules.fixAddCarModelRequest(addCarModelRequest)), CarModelEntity.class);
        carModelEntity.setItemType(CAR_MODEL);
        return modelMapperService.forResponse().map(carModelEntityService.add(carModelEntity), CarModelDTO.class);
    }

    @Override
    public CarModelDTO update(UpdateCarModelRequest updateCarModelRequest) {
        CarModelEntity carModelEntity = modelMapperService.forRequest()
                .map(carModelBusinessRules.checkUpdateCarModelRequest(
                        carModelBusinessRules.fixUpdateCarModelRequest(updateCarModelRequest)
                ), CarModelEntity.class);
        carModelEntity.setItemType(CAR_MODEL);
        return modelMapperService.forRequest().map(carModelEntityService.update(carModelEntity), CarModelDTO.class);
    }

    @Override
    public CarModelDTO getById(int id) {
        return modelMapperService.forResponse().map(carModelEntityService.getById(id), CarModelDTO.class);
    }

    @Override
    public CarModelDTO getByModelName(String modelName) {

        return modelMapperService.forResponse()
                .map(carModelEntityService
                        .getByModelName(this.carModelBusinessRules.fixName(modelName)), CarModelDTO.class);
    }

    @Override
    public List<CarModelDTO> getAll() {
        return carModelBusinessRules.checkDataList(carModelEntityService.getAll())
                .stream().map(carModel ->
                        modelMapperService.forResponse().map(carModel, CarModelDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarModelDTO> getAllByIsDeletedFalse() {
        return carModelBusinessRules.checkDataList(this.carModelEntityService.getAllByIsDeletedFalse())
                .stream().map(carModelEntity -> modelMapperService.forResponse().map(carModelEntity, CarModelDTO.class)).toList();
    }

    @Override
    public List<CarModelDTO> getAllByIsDeletedTrue() {
        return carModelBusinessRules.checkDataList(this.carModelEntityService.getAllByIsDeletedTrue())
                .stream().map(carModelEntity -> modelMapperService.forResponse().map(carModelEntity, CarModelDTO.class)).toList();
    }

    @Override
    public void delete(int id, boolean hardDelete) {

        if (hardDelete) {
            this.hardDelete(id);
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void hardDelete(int id) {
        this.carModelEntityService.delete(this.carModelEntityService.getById(id));
    }

    @Override
    public void softDelete(int id) {
        CarModelEntity carModelEntity = this.carModelEntityService.getById(id);
        carModelEntity.setIsDeleted(true);
        this.carModelEntityService.update(carModelEntity);
    }

    @Override
    public List<BrandDTO> getAllByBrandId(int brandId) {
        return this.carModelBusinessRules.checkDataList(carModelEntityService.getAllByBrandId(brandId)).stream().map(
                model -> modelMapperService.forResponse().map(model, BrandDTO.class)
        ).collect(Collectors.toList());

    }

}
