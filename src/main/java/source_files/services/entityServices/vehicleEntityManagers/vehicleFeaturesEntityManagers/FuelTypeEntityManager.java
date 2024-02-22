package source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.vehicleFeatures.FuelTypeEntity;
import source_files.dataAccess.vehicleFeaturesRespositories.FuelTypeRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.FuelTypeEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.FUEL_TYPE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class FuelTypeEntityManager implements FuelTypeEntityService {

    private final FuelTypeRepository fuelTypeRepository;

    @Override
    public FuelTypeEntity create(FuelTypeEntity fuelTypeEntity) {
        return fuelTypeRepository.save(fuelTypeEntity);
    }

    @Override
    public FuelTypeEntity update(FuelTypeEntity fuelTypeEntity) {
        return fuelTypeRepository.save(fuelTypeEntity);
    }

    @Override
    public FuelTypeEntity getById(int id) {
        return fuelTypeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(FUEL_TYPE_NOT_FOUND));
    }

    @Override
    public List<FuelTypeEntity> getAll() {
        return fuelTypeRepository.findAll();
    }

    @Override
    public List<FuelTypeEntity> getAllByDeletedState(boolean isDeleted) {
        return fuelTypeRepository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public void delete(FuelTypeEntity fuelTypeEntity) {
        fuelTypeRepository.delete(fuelTypeEntity);
    }
}
