package src.services.vehicle_features.common_features.fuel_type;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.vehicle.requests.vehicleFeatures.fuelType.CreateFuelTypeRequest;
import src.controllers.vehicle.requests.vehicleFeatures.fuelType.UpdateFuelTypeRequest;
import src.core.exception.DataNotFoundException;
import src.data.models.vehicle_entities.vehicle_features.FuelTypeEntity;
import src.repositories.vehicle_features.FuelTypeRepository;

import java.util.List;

import static src.core.exception.exception_types.NotFoundExceptionType.FUEL_TYPE_NOT_FOUND;

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
