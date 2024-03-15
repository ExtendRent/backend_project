package source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.controllers.vehicle.requests.vehicleFeatures.carBodyType.CreateCarBodyTypeRequest;
import source_files.controllers.vehicle.requests.vehicleFeatures.carBodyType.UpdateCarBodyTypeRequest;
import source_files.core.exception.DataNotFoundException;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;
import source_files.repositories.vehicleFeatures.CarBodyTypeRepository;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarBodyTypeEntityService;

import java.util.List;

import static source_files.core.exception.exceptionTypes.NotFoundExceptionType.BODY_TYPE_DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CarBodyTypeEntityServiceImpl implements CarBodyTypeEntityService {

    private final CarBodyTypeRepository repository;


    @Override
    public CarBodyTypeEntity create(CreateCarBodyTypeRequest createCarBodyTypeRequest) {
        CarBodyTypeEntity carBodyTypeEntity = CarBodyTypeEntity.carBodyTypeBuilder()
                .name(createCarBodyTypeRequest.getCarBodyTypeEntityName())
                .build();
        return this.repository.save(carBodyTypeEntity);
    }

    @Override
    public CarBodyTypeEntity update(UpdateCarBodyTypeRequest updateCarBodyTypeRequest) {
        CarBodyTypeEntity carBodyTypeEntity = CarBodyTypeEntity.carBodyTypeBuilder()
                .id(updateCarBodyTypeRequest.getId())
                .name(updateCarBodyTypeRequest.getName())
                .build();
        return repository.save(carBodyTypeEntity);
    }

    @Override
    public CarBodyTypeEntity update(CarBodyTypeEntity carBodyTypeEntity) {
        return repository.save(carBodyTypeEntity);
    }

    @Override
    public CarBodyTypeEntity getById(int id) {
        return repository.findById(id).orElseThrow(
                () -> new DataNotFoundException(BODY_TYPE_DATA_NOT_FOUND)
        );
    }

    @Override
    public void delete(CarBodyTypeEntity carBodyTypeEntity) {
        repository.delete(carBodyTypeEntity);
    }

    @Override
    public List<CarBodyTypeEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<CarBodyTypeEntity> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted);
    }


}


