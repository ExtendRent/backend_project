package source_files.services.entityServices.vehicleEntityManagers.vehicleFeaturesEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.vehicleEntities.vehicleFeatures.ShiftTypeEntity;
import source_files.dataAccess.vehicleFeaturesRespositories.ShiftTypeRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.vehicleAbstracts.vehicleFeaturesAbstracts.ShiftTypeEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.SHIFT_TYPE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ShiftTypeEntityManager implements ShiftTypeEntityService {

    private final ShiftTypeRepository shiftTypeRepository;

    @Override
    public ShiftTypeEntity create(ShiftTypeEntity shiftTypeEntity) {
        return shiftTypeRepository.save(shiftTypeEntity);
    }

    @Override
    public ShiftTypeEntity update(ShiftTypeEntity shiftTypeEntity) {
        return shiftTypeRepository.save(shiftTypeEntity);
    }

    @Override
    public ShiftTypeEntity getById(int id) {
        return shiftTypeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(SHIFT_TYPE_NOT_FOUND, "Renk bulunamadı"));
    }

    @Override
    public List<ShiftTypeEntity> getAll() {
        return shiftTypeRepository.findAll();
    }

    @Override
    public List<ShiftTypeEntity> getAllByDeletedState(boolean isDeleted) {
        return shiftTypeRepository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public void delete(ShiftTypeEntity shiftTypeEntity) {
        shiftTypeRepository.delete(shiftTypeEntity);
    }
}
