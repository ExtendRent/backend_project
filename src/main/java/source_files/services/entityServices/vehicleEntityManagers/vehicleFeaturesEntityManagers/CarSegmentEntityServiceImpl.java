package source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.controllers.vehicle.requests.vehicleFeatures.carSegment.CreateCarSegmentRequest;
import source_files.controllers.vehicle.requests.vehicleFeatures.carSegment.UpdateCarSegmentRequest;
import source_files.core.exception.DataNotFoundException;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarSegmentEntity;
import source_files.repositories.vehicleFeatures.CarSegmentRepository;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarSegmentEntityService;

import java.util.List;

import static source_files.core.exception.exceptionTypes.NotFoundExceptionType.CAR_SEGMENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CarSegmentEntityServiceImpl implements CarSegmentEntityService {
    private final CarSegmentRepository repository;

    @Override
    public CarSegmentEntity create(CreateCarSegmentRequest createCarSegmentRequest) {
        CarSegmentEntity carSegmentEntity = CarSegmentEntity.carSegmentBuilder()
                .name(createCarSegmentRequest.getName())
                .build();
        return repository.save(carSegmentEntity);
    }

    @Override
    public CarSegmentEntity update(UpdateCarSegmentRequest updateCarSegmentRequest) {
        CarSegmentEntity carSegmentEntity = CarSegmentEntity.carSegmentBuilder()
                .id(updateCarSegmentRequest.getId())
                .name(updateCarSegmentRequest.getName())
                .build();
        return repository.save(carSegmentEntity);
    }

    @Override
    public CarSegmentEntity update(CarSegmentEntity carSegmentEntity) {
        return repository.save(carSegmentEntity);
    }

    @Override
    public CarSegmentEntity getById(int id) {
        return repository.findById(id).orElseThrow(() -> new DataNotFoundException(CAR_SEGMENT_NOT_FOUND));
    }

    @Override
    public List<CarSegmentEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<CarSegmentEntity> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public void delete(CarSegmentEntity carSegmentEntity) {
        repository.delete(carSegmentEntity);
    }
}
