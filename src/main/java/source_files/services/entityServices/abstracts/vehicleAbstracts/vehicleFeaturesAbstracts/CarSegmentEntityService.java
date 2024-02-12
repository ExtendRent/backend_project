package source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarSegmentEntity;

import java.util.List;

public interface CarSegmentEntityService {

    CarSegmentEntity create(CarSegmentEntity carSegmentEntity);

    CarSegmentEntity update(CarSegmentEntity carSegmentEntity);

    CarSegmentEntity getById(int id);

    List<CarSegmentEntity> getAll();

    List<CarSegmentEntity> getAllByDeletedState(boolean isDeleted);

    void delete(CarSegmentEntity carSegmentEntity);
}
