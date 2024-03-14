package source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts;

import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.VehicleStatusRequests.CreateVehicleStatusRequest;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.VehicleStatusRequests.UpdateVehicleStatusRequest;
import source_files.data.enums.defaultDataEnums.Status.DefaultVehicleStatus;
import source_files.data.models.vehicleEntities.vehicleFeatures.VehicleStatusEntity;

import java.util.List;

public interface VehicleStatusEntityService {
    VehicleStatusEntity create(CreateVehicleStatusRequest createVehicleStatusRequest);

    VehicleStatusEntity update(UpdateVehicleStatusRequest updateVehicleStatusRequest);

    VehicleStatusEntity update(VehicleStatusEntity vehicleStatusEntity);

    VehicleStatusEntity getById(int id);

    List<VehicleStatusEntity> getAll();

    VehicleStatusEntity getByStatus(DefaultVehicleStatus status);

    void delete(VehicleStatusEntity vehicleStatusEntity);
}
