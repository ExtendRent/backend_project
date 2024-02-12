package source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarSegmentEntity;
import source_files.dataAccess.vehicleFeaturesRespositories.CarSegmentRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.CarSegmentEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.CAR_SEGMENT_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CarSegmentEntityManager implements CarSegmentEntityService {
    private final CarSegmentRepository repository;

    @Override
    public CarSegmentEntity create(CarSegmentEntity carSegmentEntity) {
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
