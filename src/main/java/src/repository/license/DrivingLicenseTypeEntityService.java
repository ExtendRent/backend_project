package src.repository.license;

import src.controller.license.request.CreateDrivingLicenseTypeRequest;
import src.controller.license.request.UpdateDrivingLicenseTypeRequest;

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
