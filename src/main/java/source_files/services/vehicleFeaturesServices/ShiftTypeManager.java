package source_files.services.vehicleFeaturesServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.itemDTOs.ShiftTypeDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.ShiftTypeEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ShiftTypeRequests.CreateShiftTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ShiftTypeRequests.UpdateShiftTypeRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.ShiftTypeBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.ShiftTypeEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.ShiftTypeService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftTypeManager implements ShiftTypeService {

    private final ShiftTypeEntityService entityService;

    private final ShiftTypeBusinessRules rules;

    @Override
    public void create(CreateShiftTypeRequest createShiftTypeRequest) {
        entityService.create(createShiftTypeRequest);
    }

    @Override
    public ShiftTypeDTO update(UpdateShiftTypeRequest updateShiftTypeRequest) {
        return entityService.update(updateShiftTypeRequest).toModel();
    }

    @Override
    public ShiftTypeDTO getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<ShiftTypeDTO> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public List<ShiftTypeDTO> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            this.entityService.delete(this.entityService.getById(id));
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        ShiftTypeEntity shiftTypeEntity = this.entityService.getById(id);
        shiftTypeEntity.setIsDeleted(true);
        shiftTypeEntity.setDeletedAt(LocalDateTime.now());
        this.entityService.update(shiftTypeEntity);
    }

    private List<ShiftTypeDTO> mapToDTOList(List<ShiftTypeEntity> entities) {
        rules.checkDataList(entities);
        return entities.stream().map(ShiftTypeEntity::toModel).toList();
    }
}
