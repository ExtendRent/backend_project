package src.services.vehicle_features.common_features.shift_type;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.vehicle.requests.vehicleFeatures.shiftType.CreateShiftTypeRequest;
import src.controllers.vehicle.requests.vehicleFeatures.shiftType.UpdateShiftTypeRequest;
import src.controllers.vehicle.responses.ShiftTypeResponse;
import src.data.models.vehicle_entities.vehicle_features.ShiftTypeEntity;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShiftTypeServiceImpl implements ShiftTypeService {

    private final ShiftTypeEntityService entityService;

    private final ShiftTypeRules rules;

    @Override
    public void create(CreateShiftTypeRequest createShiftTypeRequest) {
        createShiftTypeRequest = rules.fix(createShiftTypeRequest);
        rules.check(createShiftTypeRequest);
        entityService.create(createShiftTypeRequest);
    }

    @Override
    public ShiftTypeResponse update(UpdateShiftTypeRequest updateShiftTypeRequest) {
        updateShiftTypeRequest = rules.fix(updateShiftTypeRequest);
        rules.check(updateShiftTypeRequest);
        return entityService.update(updateShiftTypeRequest).toModel();
    }

    @Override
    public ShiftTypeResponse getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<ShiftTypeResponse> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public List<ShiftTypeResponse> getAllByDeletedState(boolean isDeleted) {
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

    private List<ShiftTypeResponse> mapToDTOList(List<ShiftTypeEntity> entities) {
        rules.checkDataList(entities);
        return entities.stream().map(ShiftTypeEntity::toModel).toList();
    }
}
