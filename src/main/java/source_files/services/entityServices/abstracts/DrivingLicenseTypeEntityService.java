package source_files.services.entityServices.abstracts;

import source_files.data.models.DrivingLicenseTypeEntity;
import source_files.data.requests.CreateDrivingLicenseTypeRequest;
import source_files.data.requests.UpdateDrivingLicenseTypeRequest;

import java.util.List;

public interface DrivingLicenseTypeEntityService {
    DrivingLicenseTypeEntity create(CreateDrivingLicenseTypeRequest createDrivingLicenseTypeRequest);

    DrivingLicenseTypeEntity update(UpdateDrivingLicenseTypeRequest updateDrivingLicenseTypeRequest);

    DrivingLicenseTypeEntity update(DrivingLicenseTypeEntity drivingLicenseTypeEntity);

    void delete(DrivingLicenseTypeEntity drivingLicenseTypeEntity);

    DrivingLicenseTypeEntity getById(int id);

    List<DrivingLicenseTypeEntity> getAll();

    List<DrivingLicenseTypeEntity> getAllByDeletedState(boolean isDeleted);
}
