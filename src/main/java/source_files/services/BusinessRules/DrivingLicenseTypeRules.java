package source_files.services.BusinessRules;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.CreateDrivingLicenseTypeRequest;
import source_files.data.requests.UpdateDrivingLicenseTypeRequest;
import source_files.dataAccess.DrivingLicenseTypeRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseItemRules;

import java.util.List;

import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.DRIVING_LICENSE_TYPE_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.DRIVING_LICENSE_TYPE_LIST_NOT_FOUND;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.DRIVING_LICENSE_TYPE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class DrivingLicenseTypeRules implements BaseItemRules {

    private final DrivingLicenseTypeRepository repository;

    public CreateDrivingLicenseTypeRequest fix(CreateDrivingLicenseTypeRequest createDrivingLicenseTypeRequest) {
        createDrivingLicenseTypeRequest.setName(fixName(createDrivingLicenseTypeRequest.getName()));
        return createDrivingLicenseTypeRequest;
    }

    public UpdateDrivingLicenseTypeRequest fix(UpdateDrivingLicenseTypeRequest updateDrivingLicenseTypeRequest) {
        updateDrivingLicenseTypeRequest.setName(fixName(updateDrivingLicenseTypeRequest.getName()));
        return updateDrivingLicenseTypeRequest;
    }

    public void check(CreateDrivingLicenseTypeRequest createDrivingLicenseTypeRequest) {
        existsByName(createDrivingLicenseTypeRequest.getName());
    }

    public void check(UpdateDrivingLicenseTypeRequest updateDrivingLicenseTypeRequest) {
        existsByNameAndIdNot(updateDrivingLicenseTypeRequest.getName(), updateDrivingLicenseTypeRequest.getId());
    }

    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(DRIVING_LICENSE_TYPE_LIST_NOT_FOUND);
        }
    }

    @Override
    public String fixName(String name) {
        return name.replace(" ", "").toUpperCase();
    }

    @Override
    public void existsByName(String name) {
        if (repository.existsByNameIgnoreCase(name)) {
            throw new AlreadyExistsException(DRIVING_LICENSE_TYPE_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByNameAndIdNot(String name, int id) {
        if (repository.existsByNameIgnoreCaseAndIdNot(name, id)) {
            throw new DataNotFoundException(DRIVING_LICENSE_TYPE_NOT_FOUND);
        }
    }
}
