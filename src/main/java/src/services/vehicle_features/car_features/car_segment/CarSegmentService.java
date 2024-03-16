package src.services.vehicle_features.car_features.car_segment;

import src.controllers.vehicle.requests.vehicleFeatures.carSegment.CreateCarSegmentRequest;
import src.controllers.vehicle.requests.vehicleFeatures.carSegment.UpdateCarSegmentRequest;
import src.controllers.vehicle.responses.CarSegmentResponse;

import java.util.List;

public interface CarSegmentService {

    CarSegmentResponse create(CreateCarSegmentRequest createCarSegmentRequest);

    CarSegmentResponse update(UpdateCarSegmentRequest updateCarSegmentRequest);

    CarSegmentResponse getById(int id);

    List<CarSegmentResponse> getAll();

    List<CarSegmentResponse> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);
}
