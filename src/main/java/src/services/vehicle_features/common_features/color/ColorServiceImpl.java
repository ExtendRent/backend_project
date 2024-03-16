package src.services.vehicle_features.common_features.color;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.vehicle.requests.vehicleFeatures.color.CreateColorRequest;
import src.controllers.vehicle.requests.vehicleFeatures.color.UpdateColorRequest;
import src.controllers.vehicle.responses.ColorResponse;
import src.data.models.vehicle_entities.vehicle_features.ColorEntity;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorEntityService entityService;
    private final ColorRules rules;

    @Override
    public void create(CreateColorRequest createColorRequest) {
        createColorRequest = rules.fix(createColorRequest);
        rules.check(createColorRequest);
        entityService.create(createColorRequest);
    }

    @Override
    public ColorResponse update(UpdateColorRequest updateColorRequest) {
        updateColorRequest = rules.fix(updateColorRequest);
        rules.check(updateColorRequest);
        return entityService.update(updateColorRequest).toModel();
    }

    @Override
    public ColorResponse getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<ColorResponse> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public List<ColorResponse> getAllByDeletedState(boolean isDeleted) {
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

    private List<ColorResponse> mapToDTOList(List<ColorEntity> colorEntities) {
        rules.checkDataList(colorEntities);
        return colorEntities.stream().map(ColorEntity::toModel).toList();
    }
}
