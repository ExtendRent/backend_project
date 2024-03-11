package source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarSegmentEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarSegmentRequests.CreateCarSegmentRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarSegmentRequests.UpdateCarSegmentRequest;

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
