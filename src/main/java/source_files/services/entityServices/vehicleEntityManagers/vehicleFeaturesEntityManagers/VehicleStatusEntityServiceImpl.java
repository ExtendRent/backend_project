package source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.enums.defaultDataEnums.Status.DefaultVehicleStatus;
import source_files.data.models.vehicleEntities.vehicleFeatures.VehicleStatusEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.VehicleStatusRequests.CreateVehicleStatusRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.VehicleStatusRequests.UpdateVehicleStatusRequest;
import source_files.dataAccess.vehicleFeaturesRespositories.VehicleStatusRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.VehicleStatusEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.VEHICLE_STATUS_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class VehicleStatusEntityServiceImpl implements VehicleStatusEntityService {

    private final VehicleStatusRepository repository;

    @Override
    public VehicleStatusEntity create(CreateVehicleStatusRequest createVehicleStatusRequest) {
        VehicleStatusEntity vehicleStatusEntity = VehicleStatusEntity.vehicleStatusBuilder()
                .name(createVehicleStatusRequest.getName())
                .vehicleStatus(createVehicleStatusRequest.getVehicleStatus())
                .build();
        return repository.save(vehicleStatusEntity);
    }

    @Override
    public VehicleStatusEntity update(UpdateVehicleStatusRequest updateVehicleStatusRequest) {
        VehicleStatusEntity vehicleStatusEntity = VehicleStatusEntity.vehicleStatusBuilder()
                .id(updateVehicleStatusRequest.getId())
                .name(updateVehicleStatusRequest.getName())
                .build();
        return repository.save(vehicleStatusEntity);
    }

    @Override
    public VehicleStatusEntity update(VehicleStatusEntity vehicleStatusEntity) {
        return repository.save(vehicleStatusEntity);
    }

    @Override
    public VehicleStatusEntity getById(int id) {
        return repository.findById(id).orElseThrow(
                () -> new DataNotFoundException(VEHICLE_STATUS_NOT_FOUND, "Araç durumu bulunamadı."));
    }

    @Override
    public List<VehicleStatusEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public VehicleStatusEntity getByStatus(DefaultVehicleStatus status) {
        return repository.findByVehicleStatus(status)
                .orElseThrow(() -> new DataNotFoundException(VEHICLE_STATUS_NOT_FOUND, "Araç durumu bulunamadı."));
    }

    @Override
    public void delete(VehicleStatusEntity vehicleStatusEntity) {
        repository.delete(vehicleStatusEntity);
    }
}
