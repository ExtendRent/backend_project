package source_files.services.vehicleService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.data.requests.vehicleRequests.CarRequests.AddCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.services.entityServices.CarEntityManager;
import source_files.services.vehicleService.abstracts.CarService;

@Service
@AllArgsConstructor
public class CarManager implements CarService {

    private final ModelMapperService modelMapperService;
    private final CarEntityManager carEntityManager;


    @Override
    public CarDTO add(AddCarRequest addCarRequest) {
        //Tek satırda yazdık. Ayrılmış hali Customer Manager sınıfında add methodunda mevcut.

        return this.modelMapperService.forResponse().map(carEntityManager
               .add(modelMapperService.forRequest().map(addCarRequest, CarEntity.class)), CarDTO.class);
    }

    @Override
    public CarDTO getById(int id) {
        return this.modelMapperService.forResponse().map(carEntityManager.getById(id),CarDTO.class);
    }

    @Override
    public CarDTO update(UpdateCarRequest updateCarRequest, int id) {

        CarEntity carEntity = this.carEntityManager.getById(id);


        if (carEntity != null) {
            carEntity = modelMapperService.forRequest().map(updateCarRequest, carEntity.getClass());
            carEntity.setId(id);
            carEntity = carEntityManager.update(carEntity);
        }


        return modelMapperService.forResponse().map(carEntity, CarDTO.class);
    }
}
