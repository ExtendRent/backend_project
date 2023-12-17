package source_files.services.entityServices.vehicleFeaturesEntityManagers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;
import source_files.dataAccess.vehicleFeaturesRespositories.CarModelRepository;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.CarModelEntityService;

import java.util.List;

@Service
@AllArgsConstructor
public class CarModelEntityManager implements CarModelEntityService {

    private final CarModelRepository carModelRepository;

    @Override
    public CarModelEntity add(CarModelEntity carModelEntity) {
        return this.carModelRepository.save(carModelEntity);
    }

    @Override
    public CarModelEntity updateCarModel(CarModelEntity carModelEntity) {
        return this.carModelRepository.save(carModelEntity);
    }

    @Override
    public CarModelEntity getCarModelById(int id) {
        return this.carModelRepository.findById(id).orElseThrow();
    }

    @Override
    public void deleteCarModel(CarModelEntity carModelEntity) {
        this.carModelRepository.delete(carModelEntity);
    }

    @Override
    public List<CarModelEntity> getAllCarModel() {
        return carModelRepository.findAll();
    }
}
