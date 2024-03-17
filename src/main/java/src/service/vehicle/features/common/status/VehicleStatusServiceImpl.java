package src.service.vehicle.features.common.status;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.vehicle.features.common.status.requests.CreateVehicleStatusRequest;
import src.controller.vehicle.features.common.status.requests.UpdateVehicleStatusRequest;
import src.controller.vehicle.features.common.status.responses.VehicleStatusResponse;
import src.repository.vehicle.features.common.status.VehicleStatusEntity;
import src.repository.vehicle.features.common.status.VehicleStatusEntityService;
import src.service.vehicle.features.common.status.model.DefaultVehicleStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleStatusServiceImpl implements VehicleStatusService {
    private final VehicleStatusEntityService entityService;
    private final VehicleStatusRules rules;

    @Override
    public void create(CreateVehicleStatusRequest createVehicleStatusRequest) {
        entityService.create(createVehicleStatusRequest);
    }

    @Override
    public VehicleStatusResponse update(UpdateVehicleStatusRequest updateVehicleStatusRequest) {
        return entityService.update(updateVehicleStatusRequest).toModel();
    }

    @Override
    public VehicleStatusResponse getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<VehicleStatusResponse> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public VehicleStatusResponse getByStatus(DefaultVehicleStatus status) {
        return entityService.getByStatus(status).toModel();
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            entityService.delete(entityService.getById(id));
        } else {
            softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        VehicleStatusEntity vehicleStatusEntity = entityService.getById(id);
        vehicleStatusEntity.setIsDeleted(true);
        vehicleStatusEntity.setDeletedAt(LocalDateTime.now());
        entityService.update(vehicleStatusEntity);
    }

    private List<VehicleStatusResponse> mapToDTOList(List<VehicleStatusEntity> vehicleStatusEntities) {
        rules.checkDataList(vehicleStatusEntities);
        return vehicleStatusEntities.stream().map(VehicleStatusEntity::toModel).toList();
    }
}
