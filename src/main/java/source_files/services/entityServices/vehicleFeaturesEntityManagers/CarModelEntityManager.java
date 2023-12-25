package source_files.services.entityServices.vehicleFeaturesEntityManagers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarModelEntity;
import source_files.dataAccess.vehicleFeaturesRespositories.CarModelRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.vehicleFeaturesAbstracts.CarModelEntityService;
import static source_files.exception.NotFoundExceptionType.MODEL_DATA_NOT_FOUND;
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
        return this.carModelRepository.save(carModelEntity);
    }

    @Override
    public CarModelEntity getById(int id) {
        return this.carModelRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException(MODEL_DATA_NOT_FOUND,"Model bulunamadı")
        );
    }

    @Override
    public CarModelEntity getByModelName(String modelName) {
        return this.carModelRepository.findByName(modelName);
    }


    @Override
    public List<CarModelEntity> getAll() {
        return this.carModelRepository.findAll();
    }

    @Override
    public List<CarModelEntity> getAllByIsDeletedFalse() {
        return this.carModelRepository.findAllByIsDeletedFalse();
    }

    @Override
    public List<CarModelEntity> getAllByIsDeletedTrue() {
        return this.carModelRepository.findAllByIsDeletedTrue();
    }

    @Override
    public List<CarModelEntity> getAllByBrandId(int id) {
        return this.carModelRepository.findAllByBrandId(id);
    }

    @Override
    public void delete(CarModelEntity carModelEntity) {
        this.carModelRepository.delete(carModelEntity);
    }


}
