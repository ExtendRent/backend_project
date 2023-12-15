package source_files.services.entityServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.CarEntity;
import source_files.dataAccess.vehicleRepositories.CarRepository;
import source_files.services.entityServices.abstracts.CarEntityService;

@AllArgsConstructor
@Service
public class CarEntityManager implements CarEntityService {

    private final CarRepository carRepository;

    @Override
    public CarEntity add(CarEntity carEntity) {
        return this.carRepository.save(carEntity);
    }
}
