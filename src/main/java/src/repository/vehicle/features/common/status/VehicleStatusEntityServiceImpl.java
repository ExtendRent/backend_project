package src.repository.vehicle.features.common.status;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.vehicle.features.common.status.request.CreateVehicleStatusRequest;
import src.controller.vehicle.features.common.status.request.UpdateVehicleStatusRequest;
import src.core.exception.DataNotFoundException;
import src.service.vehicle.features.common.status.model.DefaultVehicleStatus;

import java.util.List;

import static src.core.exception.type.NotFoundExceptionType.VEHICLE_STATUS_NOT_FOUND;

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
