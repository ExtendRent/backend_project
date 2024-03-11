package source_files.services.entityServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.DrivingLicenseTypeEntity;
import source_files.data.requests.CreateDrivingLicenseTypeRequest;
import source_files.data.requests.UpdateDrivingLicenseTypeRequest;
import source_files.dataAccess.DrivingLicenseTypeRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.DrivingLicenseTypeEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.DRIVING_LICENSE_TYPE_NOT_FOUND;

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
