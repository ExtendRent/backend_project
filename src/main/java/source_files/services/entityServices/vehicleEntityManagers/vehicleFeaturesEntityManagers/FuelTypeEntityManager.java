package source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.vehicleFeatures.FuelTypeEntity;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.FuelTypeRequests.CreateFuelTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.FuelTypeRequests.UpdateFuelTypeRequest;
import source_files.dataAccess.vehicleFeaturesRespositories.FuelTypeRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.FuelTypeEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.FUEL_TYPE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FuelTypeEntityManager implements FuelTypeEntityService {

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
