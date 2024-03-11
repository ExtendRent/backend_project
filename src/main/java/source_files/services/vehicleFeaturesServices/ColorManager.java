package source_files.services.vehicleFeaturesServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.itemDTOs.ColorDTO;
import source_files.data.models.vehicleEntities.vehicleFeatures.ColorEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.CreateColorRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.UpdateColorRequest;
import source_files.services.BusinessRules.vehicleFeaturesBusinessRules.ColorBusinessRules;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.ColorEntityService;
import source_files.services.vehicleFeaturesServices.abstracts.ColorService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorManager implements ColorService {

    private final ColorEntityService entityService;
    private final ColorBusinessRules rules;

    @Override
    public void create(CreateColorRequest createColorRequest) {
        createColorRequest = rules.fixCreateColorRequest(createColorRequest);
        rules.checkCreateColorRequest(createColorRequest);
        entityService.create(createColorRequest);
    }

    @Override
    public ColorDTO update(UpdateColorRequest updateColorRequest) {
        updateColorRequest = rules.fixUpdateColorRequest(updateColorRequest);
        rules.checkUpdateColorRequest(updateColorRequest);
        return entityService.update(updateColorRequest).toModel();
    }

    @Override
    public ColorDTO getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<ColorDTO> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public List<ColorDTO> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            entityService.delete(this.entityService.getById(id));
        } else {
            softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        ColorEntity colorEntity = entityService.getById(id);
        colorEntity.setIsDeleted(true);
        colorEntity.setDeletedAt(LocalDateTime.now());
        entityService.update(colorEntity);
    }

    private List<ColorDTO> mapToDTOList(List<ColorEntity> colorEntities) {
        rules.checkDataList(colorEntities);
        return colorEntities.stream().map(ColorEntity::toModel).toList();
    }
}
