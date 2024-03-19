package src.repository.vehicle.features.car.segment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.vehicle.features.car.segment.request.CreateCarSegmentRequest;
import src.controller.vehicle.features.car.segment.request.UpdateCarSegmentRequest;
import src.core.exception.DataNotFoundException;

import java.util.List;

import static src.core.exception.type.NotFoundExceptionType.CAR_SEGMENT_NOT_FOUND;

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
