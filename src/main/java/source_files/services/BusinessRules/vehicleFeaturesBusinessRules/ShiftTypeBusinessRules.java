package source_files.services.BusinessRules.vehicleFeaturesBusinessRules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ShiftTypeRequests.CreateShiftTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ShiftTypeRequests.UpdateShiftTypeRequest;
import source_files.dataAccess.vehicleFeaturesRespositories.ShiftTypeRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseItemBusinessRulesService;

import java.util.List;

import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.SHIFT_TYPE_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.SHIFT_TYPE_LIST_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class ShiftTypeBusinessRules implements BaseItemBusinessRulesService {
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
