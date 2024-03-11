package source_files.services.vehicleFeaturesServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.itemDTOs.VehicleStatusDTO;
import source_files.data.enums.defaultDataEnums.Status.DefaultVehicleStatus;
import source_files.data.models.vehicleEntities.vehicleFeatures.VehicleStatusEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.VehicleStatusRequests.CreateVehicleStatusRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.VehicleStatusRequests.UpdateVehicleStatusRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.VehicleStatusBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.VehicleStatusEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.VehicleStatusService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VehicleStatusServiceImpl implements VehicleStatusService {
    private final VehicleStatusEntityService entityService;
    private final VehicleStatusBusinessRules rules;

    @Override
    public void create(CreateVehicleStatusRequest createVehicleStatusRequest) {
        entityService.create(createVehicleStatusRequest);
    }

    @Override
    public VehicleStatusDTO update(UpdateVehicleStatusRequest updateVehicleStatusRequest) {
        return entityService.update(updateVehicleStatusRequest).toModel();
    }

    @Override
    public VehicleStatusDTO getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<VehicleStatusDTO> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public VehicleStatusDTO getByStatus(DefaultVehicleStatus status) {
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

    private List<VehicleStatusDTO> mapToDTOList(List<VehicleStatusEntity> vehicleStatusEntities) {
        rules.checkDataList(vehicleStatusEntities);
        return vehicleStatusEntities.stream().map(VehicleStatusEntity::toModel).toList();
    }
}
