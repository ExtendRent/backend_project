package source_files.services.vehicleService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.data.requests.vehicleRequests.CarRequests.AddCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.dataAccess.vehicleRepositories.CarRepository;
import source_files.services.entityServices.CarEntityManager;
import source_files.services.vehicleService.abstracts.CarService;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private final ModelMapperService modelMapperService;
    private final CarEntityManager carEntityManager;

    private final CarRepository carRepository;


    @Override
    public CarDTO add(AddCarRequest addCarRequest) {
        //Tek satırda yazdık. Ayrılmış hali Customer Manager sınıfında add methodunda mevcut.

        return this.modelMapperService.forResponse().map(carEntityManager
                .add(modelMapperService.forRequest().map(addCarRequest, CarEntity.class)), CarDTO.class);
    }

    @Override
    public CarDTO getById(int id) {
        return this.modelMapperService.forResponse().map(carEntityManager.getById(id), CarDTO.class);
    }

    @Override
    public CarDTO update(UpdateCarRequest updateCarRequest) {

        //CarEntity carEntity = carEntityManager.getById(updateCarRequest.getId());

        CarEntity carEntity = modelMapperService.forRequest().map(updateCarRequest, CarEntity.class);
        carEntity = carEntityManager.update(carEntity);

        return modelMapperService.forResponse().map(carEntity, CarDTO.class);

    }
}
