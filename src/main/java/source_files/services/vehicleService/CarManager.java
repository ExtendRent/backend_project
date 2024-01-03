package source_files.services.vehicleService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.data.requests.vehicleRequests.CarRequests.AddCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.services.BusinessRules.CarBusinessRules;
import source_files.services.entityServices.abstracts.CarEntityService;
import source_files.services.vehicleService.abstracts.CarService;

import java.util.List;
import java.util.stream.Collectors;

import static source_files.data.types.VehicleType.CAR;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private final ModelMapperService modelMapperService;
    private final CarEntityService carEntityService;
    private CarBusinessRules businessRules;


    @Override
    public CarDTO add(AddCarRequest addCarRequest) {
        //TODO:DTO DAN ENTİTYLER NULL GELİYOR TEKRAR KONTROL ET

        //CarEntity carEntity = modelMapperService.forRequest().map(addCarRequest, CarEntity.class); //ESKİ KOD !

        CarEntity carEntity = modelMapperService.forRequest().map(this.businessRules                 //YENİ KOD
                .checkAddCarRequest(businessRules.fixAddCarRequest(addCarRequest)), CarEntity.class);

        carEntity.setVehicleType(CAR);
        return modelMapperService.forResponse().map(carEntityService.add(carEntity), CarDTO.class);
    }

    @Override
    public CarDTO getById(int id) {
        return this.modelMapperService.forResponse().map(carEntityService.getById(id), CarDTO.class);
    }

    @Override
    public CarDTO update(UpdateCarRequest updateCarRequest) {

        CarEntity carEntity = modelMapperService.forRequest().map(updateCarRequest, CarEntity.class);
        carEntity = carEntityService.update(carEntity);
        carEntity.setVehicleType(CAR);

        return modelMapperService.forResponse().map(carEntity, CarDTO.class);

    }

    @Override
    public List<CarDTO> getAll() throws Exception {
        try {
            return carEntityService.getAll().stream()
                    .map(carEntity -> this.modelMapperService.forResponse()
                            .map(carEntity, CarDTO.class)).collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e);
        }


    }

    @Override
    public List<CarDTO> getAllByIsDeletedFalse() {
        return this.carEntityService.getAllByIsDeletedFalse()
                .stream().map(carEntity -> modelMapperService.forResponse().map(carEntity, CarDTO.class)).toList();
    }

    @Override
    public List<CarDTO> getAllByIsDeletedTrue() {
        return this.carEntityService.getAllByIsDeletedTrue()
                .stream().map(carEntity -> modelMapperService.forResponse().map(carEntity, CarDTO.class)).toList();
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
        this.carEntityService.delete(this.carEntityService.getById(id));
    }

    @Override
    public void softDelete(int id) {
        CarEntity carEntity = this.carEntityService.getById(id);
        carEntity.setIsDeleted(true);
        this.carEntityService.update(carEntity);
    }
}
