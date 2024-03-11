package source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts;

import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.CarBodyTypeEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.CreateCarBodyTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.UpdateCarBodyTypeRequest;

import java.util.List;

public interface CarBodyTypeEntityService {
    CarBodyTypeEntity create(CreateCarBodyTypeRequest createCarBodyTypeRequest);

    CarBodyTypeEntity update(UpdateCarBodyTypeRequest updateCarBodyTypeRequest);

    CarBodyTypeEntity update(CarBodyTypeEntity carBodyTypeEntity);

    CarBodyTypeEntity getById(int id);

    void delete(CarBodyTypeEntity carBodyTypeEntity);

    List<CarBodyTypeEntity> getAll();

    List<CarBodyTypeEntity> getAllByDeletedState(boolean isDeleted);
}
