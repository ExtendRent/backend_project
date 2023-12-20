package source_files.services.vehicleService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.data.requests.vehicleRequests.CarRequests.AddCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.dataAccess.vehicleRepositories.CarRepository;
import source_files.services.BusinessRules.CarBusinessRules;
import source_files.services.entityServices.abstracts.CarEntityService;
import source_files.services.vehicleService.abstracts.CarService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private final ModelMapperService modelMapperService;
    private final CarEntityService carEntityService;

    private final CarRepository carRepository;
    private CarBusinessRules businessRules;


    @Override
    public CarDTO add(AddCarRequest addCarRequest) {
        //TODO:DTO DAN ENTİTYLER NULL GELİYOR TEKRAR KONTROL ET

        //addCarRequest.setLicensePlate(addCarRequest.getLicensePlate().toUpperCase().replaceAll("\s", ""));
        String editPlate = this.businessRules.licensePlateUnique(addCarRequest.getLicensePlate());
        addCarRequest.setLicensePlate(editPlate);
        CarEntity carEntity = modelMapperService.forRequest().map(addCarRequest, CarEntity.class);
        businessRules.existByModelIdAndColorId(addCarRequest.getModelId(), addCarRequest.getColorId());
        CarDTO carDTO = modelMapperService.forResponse().map(carEntityService.add(carEntity), CarDTO.class);
        return carDTO;
    }

    @Override
    public CarDTO getById(int id) {
        return this.modelMapperService.forResponse().map(carEntityService.getById(id), CarDTO.class);
    }

    @Override
    public CarDTO update(UpdateCarRequest updateCarRequest) {

        //CarEntity carEntity = carEntityManager.getById(updateCarRequest.getId());

        CarEntity carEntity = modelMapperService.forRequest().map(updateCarRequest, CarEntity.class);
        carEntity = carEntityService.update(carEntity);

        return modelMapperService.forResponse().map(carEntity, CarDTO.class);

    }

    @Override
    public List<CarDTO> getAll() {

        return carEntityService.getAll().stream()
                .map(carEntity -> this.modelMapperService.forResponse()
                        .map(carEntity, CarDTO.class)).collect(Collectors.toList());

    }

    @Override
    public void delete(int id) {
        this.carEntityService.delete(this.carEntityService.getById(id));
    }
}
