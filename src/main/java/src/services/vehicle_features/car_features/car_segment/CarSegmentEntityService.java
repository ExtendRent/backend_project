package src.services.vehicle_features.car_features.car_segment;

import src.controllers.vehicle.requests.vehicleFeatures.carSegment.CreateCarSegmentRequest;
import src.controllers.vehicle.requests.vehicleFeatures.carSegment.UpdateCarSegmentRequest;
import src.data.models.vehicle_entities.vehicle_features.car_features.CarSegmentEntity;

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
