package source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.controllers.vehicle.requests.vehicleFeatures.fuelType.CreateFuelTypeRequest;
import source_files.controllers.vehicle.requests.vehicleFeatures.fuelType.UpdateFuelTypeRequest;
import source_files.core.exception.DataNotFoundException;
import source_files.data.models.vehicleEntities.vehicleFeatures.FuelTypeEntity;
import source_files.repositories.vehicleFeatures.FuelTypeRepository;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.FuelTypeEntityService;

import java.util.List;

import static source_files.core.exception.exceptionTypes.NotFoundExceptionType.FUEL_TYPE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FuelTypeEntityServiceImpl implements FuelTypeEntityService {

    private final FuelTypeRepository repository;

    @Override
    public FuelTypeEntity create(CreateFuelTypeRequest createFuelTypeRequest) {
        FuelTypeEntity fuelTypeEntity = FuelTypeEntity.fuelTypeBuilder()
                .name(createFuelTypeRequest.getName())
                .build();
        return repository.save(fuelTypeEntity);
    }

    @Override
    public FuelTypeEntity update(UpdateFuelTypeRequest updateFuelTypeRequest) {
        FuelTypeEntity fuelTypeEntity = FuelTypeEntity.fuelTypeBuilder()
                .id(updateFuelTypeRequest.getId())
                .name(updateFuelTypeRequest.getName())
                .build();
        return repository.save(fuelTypeEntity);
    }

    @Override
    public FuelTypeEntity update(FuelTypeEntity fuelTypeEntity) {
        return repository.save(fuelTypeEntity);
    }

    @Override
    public FuelTypeEntity getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(FUEL_TYPE_NOT_FOUND));
    }

    @Override
    public List<FuelTypeEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<FuelTypeEntity> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public void delete(FuelTypeEntity fuelTypeEntity) {
        repository.delete(fuelTypeEntity);
    }
}
