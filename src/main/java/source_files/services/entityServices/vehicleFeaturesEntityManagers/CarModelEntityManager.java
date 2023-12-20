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
    public CarModelEntity update(CarModelEntity carModelEntity) {
        return null;
    }

    @Override
    public CarModelEntity getById(int id) {
        return null;
    }

    @Override
    public List<CarModelEntity> getAll() {
        return null;
    }

    @Override
    public List<CarModelEntity> getAllByIsDeletedFalse() {
        return null;
    }

    @Override
    public List<CarModelEntity> getAllByIsDeletedTrue() {
        return null;
    }

    @Override
    public void delete(CarModelEntity carModelEntity) {

    }


}
