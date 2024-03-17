package src.service.vehicle.features.car.segment;

import src.controller.vehicle.features.car.segment.requests.CreateCarSegmentRequest;
import src.controller.vehicle.features.car.segment.requests.UpdateCarSegmentRequest;
import src.controller.vehicle.features.car.segment.responses.CarSegmentResponse;

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
