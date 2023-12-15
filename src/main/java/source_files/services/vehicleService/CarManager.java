package source_files.services.vehicleService;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.data.requests.vehicleRequests.CarRequests.AddCarRequest;
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
        return null;
    }
}
