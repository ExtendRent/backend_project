package source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.ColorRequests.CreateColorRequest;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.ColorRequests.UpdateColorRequest;
import source_files.data.models.vehicleEntities.vehicleFeatures.ColorEntity;
import source_files.exception.DataNotFoundException;
import source_files.repositories.vehicleFeatures.ColorRepository;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.ColorEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.COLOR_DATA_NOT_FOUND;

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