package source_files.services.vehicleFeaturesServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
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

    private final ShiftTypeEntityService shiftTypeEntityService;
    private final ModelMapperService mapper;

    private final ShiftTypeBusinessRules rules;

    @Override
    public void create(CreateShiftTypeRequest createShiftTypeRequest) {
        shiftTypeEntityService.create(mapper.forRequest()
                .map(createShiftTypeRequest, ShiftTypeEntity.class));
    }

    @Override
    public ShiftTypeDTO update(UpdateShiftTypeRequest updateShiftTypeRequest) {
        return mapper.forResponse().map(shiftTypeEntityService
                .update(mapper.forRequest().map(updateShiftTypeRequest, ShiftTypeEntity.class)), ShiftTypeDTO.class);
    }

    @Override
    public ShiftTypeDTO getById(int id) {
        return mapper.forResponse().map(shiftTypeEntityService.getById(id), ShiftTypeDTO.class);
    }

    @Override
    public List<ShiftTypeDTO> getAll() {
        return rules.checkDataList(shiftTypeEntityService.getAll())
                .stream().map(shift -> mapper.forResponse().map(shift, ShiftTypeDTO.class)
                ).toList();
    }

    @Override
    public List<ShiftTypeDTO> getAllByDeletedState(boolean isDeleted) {
        return rules.checkDataList(shiftTypeEntityService
                .getAllByDeletedState(isDeleted)).stream().map(
                shift -> mapper.forResponse().map(shift, ShiftTypeDTO.class)
        ).toList();
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            this.shiftTypeEntityService.delete(this.shiftTypeEntityService.getById(id));
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        ShiftTypeEntity shiftTypeEntity = this.shiftTypeEntityService.getById(id);
        shiftTypeEntity.setIsDeleted(true);
        shiftTypeEntity.setDeletedAt(LocalDateTime.now());
        this.shiftTypeEntityService.update(shiftTypeEntity);
    }
}
