package source_files.services.vehicleFeatures.abstracts;

import source_files.controllers.vehicle.dtos.CarSegmentDTO;
import source_files.controllers.vehicle.requests.vehicleFeatures.carSegment.CreateCarSegmentRequest;
import source_files.controllers.vehicle.requests.vehicleFeatures.carSegment.UpdateCarSegmentRequest;

import java.util.List;

public interface CarSegmentService {

    CarSegmentDTO create(CreateCarSegmentRequest createCarSegmentRequest);

    CarSegmentDTO update(UpdateCarSegmentRequest updateCarSegmentRequest);

    CarSegmentDTO getById(int id);

    List<CarSegmentDTO> getAll();

    List<CarSegmentDTO> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);
}
