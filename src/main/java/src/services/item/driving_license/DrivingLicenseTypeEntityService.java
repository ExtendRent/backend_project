package src.services.item.driving_license;

import src.controllers.item.requests.CreateDrivingLicenseTypeRequest;
import src.controllers.item.requests.UpdateDrivingLicenseTypeRequest;
import src.data.models.DrivingLicenseTypeEntity;

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
