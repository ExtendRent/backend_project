package src.repository.vehicle.features.common.fuel;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.vehicle.features.common.fuel.request.CreateFuelTypeRequest;
import src.controller.vehicle.features.common.fuel.request.UpdateFuelTypeRequest;
import src.core.exception.DataNotFoundException;

import java.util.List;

import static src.core.exception.type.NotFoundExceptionType.FUEL_TYPE_NOT_FOUND;

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
