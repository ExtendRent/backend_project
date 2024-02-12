package source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts;

import source_files.data.enums.Status.DefaultVehicleStatus;
import source_files.data.models.vehicleEntities.vehicleFeatures.VehicleStatusEntity;

import java.util.List;

public interface VehicleStatusEntityService {
    VehicleStatusEntity create(VehicleStatusEntity vehicleStatusEntity);

    VehicleStatusEntity update(VehicleStatusEntity vehicleStatusEntity);

    VehicleStatusEntity getById(int id);

    List<VehicleStatusEntity> getAll();

    VehicleStatusEntity getByStatus(DefaultVehicleStatus status);

    void delete(VehicleStatusEntity vehicleStatusEntity);
}
