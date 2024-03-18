package src.service.license;

import src.controller.license.requests.CreateDrivingLicenseTypeRequest;
import src.controller.license.requests.UpdateDrivingLicenseTypeRequest;
import src.controller.license.responses.DrivingLicenseTypeResponse;

import java.util.List;

public interface DrivingLicenseTypeService {

    void create(CreateDrivingLicenseTypeRequest createDrivingLicenseTypeRequest);

    DrivingLicenseTypeResponse update(UpdateDrivingLicenseTypeRequest updateDrivingLicenseTypeRequest);

    DrivingLicenseTypeResponse getById(int id);

    List<DrivingLicenseTypeResponse> getAll();

    List<DrivingLicenseTypeResponse> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);
}
