package src.repository.license;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.license.request.CreateDrivingLicenseTypeRequest;
import src.controller.license.request.UpdateDrivingLicenseTypeRequest;
import src.core.exception.DataNotFoundException;

import java.util.List;

import static src.core.exception.type.NotFoundExceptionType.DRIVING_LICENSE_TYPE_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class DrivingLicenseTypeEntityServiceImpl implements DrivingLicenseTypeEntityService {

    private final DrivingLicenseTypeRepository repository;


    @Override
    public DrivingLicenseTypeEntity create(CreateDrivingLicenseTypeRequest createDrivingLicenseTypeRequest) {
        DrivingLicenseTypeEntity drivingLicenseTypeEntity = DrivingLicenseTypeEntity.drivingLicenseTypeBuilder()
                .name(createDrivingLicenseTypeRequest.getName())
                .description(createDrivingLicenseTypeRequest.getDescription())
                .licenseLevel(createDrivingLicenseTypeRequest.getLicenseLevel())
                .build();
        return repository.save(drivingLicenseTypeEntity);
    }

    @Override
    public DrivingLicenseTypeEntity update(UpdateDrivingLicenseTypeRequest updateDrivingLicenseTypeRequest) {
        DrivingLicenseTypeEntity drivingLicenseTypeEntity = DrivingLicenseTypeEntity.drivingLicenseTypeBuilder()
                .id(updateDrivingLicenseTypeRequest.getId())
                .name(updateDrivingLicenseTypeRequest.getName())
                .description(updateDrivingLicenseTypeRequest.getDescription())
                .licenseLevel(updateDrivingLicenseTypeRequest.getLicenseLevel())
                .build();
        return repository.save(drivingLicenseTypeEntity);
    }

    @Override
    public DrivingLicenseTypeEntity update(DrivingLicenseTypeEntity drivingLicenseTypeEntity) {
        return repository.save(drivingLicenseTypeEntity);
    }

    @Override
    public void delete(DrivingLicenseTypeEntity drivingLicenseTypeEntity) {
        repository.delete(drivingLicenseTypeEntity);
    }

    @Override
    public DrivingLicenseTypeEntity getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(DRIVING_LICENSE_TYPE_NOT_FOUND));
    }

    @Override
    public List<DrivingLicenseTypeEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<DrivingLicenseTypeEntity> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted);
    }
}
