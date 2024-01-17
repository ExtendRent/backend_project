package source_files.services.vehicleFeaturesServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.DTO.itemDTOs.CarModelDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.AddCarModelRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.UpdateCarModelRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.CarModelBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarModelEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.CarModelService;

import java.util.List;
import java.util.stream.Collectors;

import static source_files.data.types.itemTypes.ItemType.CAR_MODEL;

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
    public List<CarModelDTO> getAllByDeletedState(boolean isDeleted) {
        return carModelBusinessRules.checkDataList(carModelEntityService.getAllByDeletedState(isDeleted))
                .stream().map(carModel ->
                        modelMapperService.forResponse().map(carModel, CarModelDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(int id, boolean hardDelete) {

        if (hardDelete) {
            this.carModelEntityService.delete(this.carModelEntityService.getById(id));
        } else {
            this.softDelete(id);
        }
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
