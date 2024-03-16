package src.services.vehicle_features.common_features.shift_type;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.vehicle.requests.vehicleFeatures.shiftType.CreateShiftTypeRequest;
import src.controllers.vehicle.requests.vehicleFeatures.shiftType.UpdateShiftTypeRequest;
import src.core.exception.DataNotFoundException;
import src.data.models.vehicle_entities.vehicle_features.ShiftTypeEntity;
import src.repositories.vehicle_features.ShiftTypeRepository;

import java.util.List;

import static src.core.exception.exception_types.NotFoundExceptionType.SHIFT_TYPE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class ShiftTypeEntityServiceImpl implements ShiftTypeEntityService {

    private final ShiftTypeRepository repository;

    @Override
    public ShiftTypeEntity create(CreateShiftTypeRequest createShiftTypeRequest) {
        ShiftTypeEntity shiftTypeEntity = ShiftTypeEntity.shiftTypeBuilder()
                .name(createShiftTypeRequest.getName())
                .build();
        return repository.save(shiftTypeEntity);
    }

    @Override
    public ShiftTypeEntity update(UpdateShiftTypeRequest updateShiftTypeRequest) {
        ShiftTypeEntity shiftTypeEntity = ShiftTypeEntity.shiftTypeBuilder()
                .id(updateShiftTypeRequest.getId())
                .name(updateShiftTypeRequest.getName())
                .build();
        return repository.save(shiftTypeEntity);
    }

    @Override
    public ShiftTypeEntity update(ShiftTypeEntity shiftTypeEntity) {
        return repository.save(shiftTypeEntity);
    }

    @Override
    public ShiftTypeEntity getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(SHIFT_TYPE_NOT_FOUND));
    }

    @Override
    public List<ShiftTypeEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<ShiftTypeEntity> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public void delete(ShiftTypeEntity shiftTypeEntity) {
        repository.delete(shiftTypeEntity);
    }
}
