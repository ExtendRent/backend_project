package src.repository.vehicle.features.car.segment;

import src.controller.vehicle.features.car.segment.requests.CreateCarSegmentRequest;
import src.controller.vehicle.features.car.segment.requests.UpdateCarSegmentRequest;

import java.util.List;

public interface CarSegmentEntityService {

    CarSegmentEntity create(CreateCarSegmentRequest createCarSegmentRequest);

    CarSegmentEntity update(UpdateCarSegmentRequest updateCarSegmentRequest);

    CarSegmentEntity update(CarSegmentEntity carSegmentEntity);

    CarSegmentEntity getById(int id);

    List<CarSegmentEntity> getAll();

    List<CarSegmentEntity> getAllByDeletedState(boolean isDeleted);

    void delete(CarSegmentEntity carSegmentEntity);
}
