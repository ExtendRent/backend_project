package src.services.vehicle_features.common_features.shift_type;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.vehicle.requests.vehicleFeatures.shiftType.CreateShiftTypeRequest;
import src.controllers.vehicle.requests.vehicleFeatures.shiftType.UpdateShiftTypeRequest;
import src.core.exception.AlreadyExistsException;
import src.core.exception.DataNotFoundException;
import src.repositories.vehicle_features.ShiftTypeRepository;
import src.services.businessrules.abstracts.BaseItemRules;

import java.util.List;

import static src.core.exception.exception_types.AlreadyExistsExceptionType.SHIFT_TYPE_ALREADY_EXISTS;
import static src.core.exception.exception_types.NotFoundExceptionType.SHIFT_TYPE_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class ShiftTypeRules implements BaseItemRules {
    private final ShiftTypeRepository repository;

    public CreateShiftTypeRequest fix(CreateShiftTypeRequest createShiftTypeRequest) {
        createShiftTypeRequest.setName(fixName(createShiftTypeRequest.getName()));
        return createShiftTypeRequest;
    }

    public UpdateShiftTypeRequest fix(UpdateShiftTypeRequest updateShiftTypeRequest) {
        updateShiftTypeRequest.setName(fixName(updateShiftTypeRequest.getName()));
        return updateShiftTypeRequest;
    }

    public void check(CreateShiftTypeRequest createShiftTypeRequest) {
        existsByName(createShiftTypeRequest.getName());
    }

    public void check(UpdateShiftTypeRequest updateShiftTypeRequest) {
        existsByNameAndIdNot(updateShiftTypeRequest.getName(), updateShiftTypeRequest.getId());
    }

    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(SHIFT_TYPE_LIST_NOT_FOUND);
        }
    }

    @Override
    public String fixName(String name) {
        return name;
    }

    @Override
    public void existsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new AlreadyExistsException(SHIFT_TYPE_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {
        if (repository.existsByNameIgnoreCaseAndIdNot(name, id)) {
            throw new AlreadyExistsException(SHIFT_TYPE_ALREADY_EXISTS);
        }
    }
}
