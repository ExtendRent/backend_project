package source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;
import source_files.dataAccess.vehicleFeaturesRespositories.CarBodyTypeRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarBodyTypeEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.BODY_TYPE_DATA_NOT_FOUND;

@Service
@AllArgsConstructor
public class CarBodyTypeEntityManager implements CarBodyTypeEntityService {

    private final CarBodyTypeRepository carBodyTypeRepository;


    @Override
    public CarBodyTypeEntity create(CarBodyTypeEntity carBodyTypeEntity) {
        return this.carBodyTypeRepository.save(carBodyTypeEntity);
    }

    @Override
    public CarBodyTypeEntity update(CarBodyTypeEntity carBodyTypeEntity) {
        return this.create(carBodyTypeEntity);
    }

    @Override
    public CarBodyTypeEntity getById(int id) {
        return carBodyTypeRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException(BODY_TYPE_DATA_NOT_FOUND, "Body Type bulunamadı.")
        );
    }

    @Override
    public void delete(CarBodyTypeEntity carBodyTypeEntity) {
        carBodyTypeRepository.delete(carBodyTypeEntity);
    }

    @Override
    public List<CarBodyTypeEntity> getAll() {
        return carBodyTypeRepository.findAll();
    }

    @Override
    public List<CarBodyTypeEntity> getAllByDeletedState(boolean isDeleted) {
        return carBodyTypeRepository.findAllByIsDeleted(isDeleted);
    }


}


