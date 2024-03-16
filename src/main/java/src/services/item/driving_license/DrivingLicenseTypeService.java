package src.services.item.driving_license;

import src.controllers.item.requests.CreateDrivingLicenseTypeRequest;
import src.controllers.item.requests.UpdateDrivingLicenseTypeRequest;
import src.controllers.item.responses.DrivingLicenseTypeResponse;

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
