package src.services.vehicle_features.common_features.color;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.vehicle.requests.vehicleFeatures.color.CreateColorRequest;
import src.controllers.vehicle.requests.vehicleFeatures.color.UpdateColorRequest;
import src.core.exception.DataNotFoundException;
import src.data.models.vehicle_entities.vehicle_features.ColorEntity;
import src.repositories.vehicle_features.ColorRepository;

import java.util.List;

import static src.core.exception.exception_types.NotFoundExceptionType.COLOR_DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ColorEntityServiceImpl implements ColorEntityService {

    private final ColorRepository repository;

    @Override
    public ColorEntity create(CreateColorRequest createColorRequest) {
        ColorEntity colorEntity = ColorEntity.colorBuilder()
                .name(createColorRequest.getColorEntityName())
                .build();
        return repository.save(colorEntity);
    }

    @Override
    public ColorEntity update(UpdateColorRequest updateColorRequest) {
        ColorEntity colorEntity = ColorEntity.colorBuilder()
                .id(updateColorRequest.getId())
                .name(updateColorRequest.getName())
                .build();
        return repository.save(colorEntity);
    }

    @Override
    public ColorEntity update(ColorEntity colorEntity) {
        return repository.save(colorEntity);
    }


    @Override
    public ColorEntity getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(COLOR_DATA_NOT_FOUND));
    }

    @Override
    public void delete(ColorEntity colorEntity) {
        repository.delete(colorEntity);
    }


    @Override
    public List<ColorEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<ColorEntity> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted);
    }
}