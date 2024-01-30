package source_files.services.vehicleFeaturesServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.itemDTOs.VehicleStatusDTO;
import source_files.data.Status.DefaultVehicleStatus;
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
public class VehicleStatusManager implements VehicleStatusService {
    private final VehicleStatusEntityService vehicleStatusEntityService;
    private final ModelMapperService mapper;
    private final VehicleStatusBusinessRules rules;

    @Override
    public void create(CreateVehicleStatusRequest createVehicleStatusRequest) {
        vehicleStatusEntityService.create(mapper.forRequest().map(createVehicleStatusRequest, VehicleStatusEntity.class));
    }

    @Override
    public VehicleStatusDTO update(UpdateVehicleStatusRequest updateVehicleStatusRequest) {
        VehicleStatusEntity vehicleStatusEntity = vehicleStatusEntityService.getById(updateVehicleStatusRequest.getId());
        vehicleStatusEntity.setName(updateVehicleStatusRequest.getName());
        return mapper.forResponse().map(vehicleStatusEntityService.update(vehicleStatusEntity), VehicleStatusDTO.class);

    }

    @Override
    public VehicleStatusDTO getById(int id) {
        return mapper.forResponse().map(vehicleStatusEntityService.getById(id), VehicleStatusDTO.class);
    }

    @Override
    public List<VehicleStatusDTO> getAll() {
        return rules.checkDataList(vehicleStatusEntityService.getAll()).stream()
                .map(statusEntity ->
                        mapper.forResponse().map(statusEntity, VehicleStatusDTO.class)
                ).toList();
    }

    @Override
    public VehicleStatusDTO getByStatus(DefaultVehicleStatus status) {
        return mapper.forResponse().map(vehicleStatusEntityService.getByStatus(status), VehicleStatusDTO.class);
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            this.vehicleStatusEntityService.delete(this.vehicleStatusEntityService.getById(id));
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        VehicleStatusEntity vehicleStatusEntity = this.vehicleStatusEntityService.getById(id);
        vehicleStatusEntity.setIsDeleted(true);
        vehicleStatusEntity.setDeletedAt(LocalDateTime.now());
        this.vehicleStatusEntityService.update(vehicleStatusEntity);
    }
}
