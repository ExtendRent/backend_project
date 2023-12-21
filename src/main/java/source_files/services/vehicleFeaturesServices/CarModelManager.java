package source_files.services.vehicleFeaturesServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.CarModelDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.CarModelRequests.AddCarModelRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.CarModelRequests.UpdateCarModelRequest;
import source_files.services.BusinessRules.BrandBusinessRules;
import source_files.services.BusinessRules.CarModelBusinessRules;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.CarModelEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.CarModelService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarModelManager implements CarModelService {

    private final CarModelEntityService carModelEntityService;
    private final ModelMapperService modelMapperService;
    private final CarModelBusinessRules carModelBusinessRules;
    private final BrandBusinessRules brandBusinessRules;

    @Override
    public CarModelDTO add(AddCarModelRequest addCarModelRequest) {

        carModelBusinessRules.existsByName(addCarModelRequest.getName());
        CarModelEntity carModelEntity = new CarModelEntity();
        CarModelEntity carModel = modelMapperService.forRequest().map(addCarModelRequest, CarModelEntity.class);
        carModel.setId(carModelEntity.getId());

        CarModelDTO carModelDTO = modelMapperService.forResponse().map(carModelEntityService.add(carModel), CarModelDTO.class);
        carModelDTO.setName(carModel.getName());
        return carModelDTO;
    }

    @Override
    public CarModelDTO update(UpdateCarModelRequest updateCarModelRequest) {
        CarModelEntity carModelEntity = modelMapperService.forRequest().map(updateCarModelRequest, CarModelEntity.class);
        carModelEntity = carModelEntityService.update(carModelEntity);
        return modelMapperService.forResponse().map(carModelEntity, CarModelDTO.class);
    }

    @Override
    public CarModelDTO getById(int id) {
        return modelMapperService.forResponse().map(carModelEntityService.getById(id), CarModelDTO.class);
    }

    @Override
    public List<CarModelDTO> getAll() {
        List<CarModelEntity> carModelEntities = carModelEntityService.getAll();
        return carModelEntities.stream().map(carModel ->
                modelMapperService.forResponse().map(carModel, CarModelDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CarModelDTO> getAllByIsDeletedFalse() {
        return this.carModelEntityService.getAllByIsDeletedFalse()
                .stream().map(carModelEntity -> modelMapperService.forResponse().map(carModelEntity, CarModelDTO.class)).toList();
    }

    @Override
    public List<CarModelDTO> getAllByIsDeletedTrue() {
        return this.carModelEntityService.getAllByIsDeletedTrue()
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

    }

}
