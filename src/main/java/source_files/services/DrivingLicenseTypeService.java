package source_files.services;

import source_files.controllers.item.dtos.DrivingLicenseTypeDTO;
import source_files.controllers.item.requests.CreateDrivingLicenseTypeRequest;
import source_files.controllers.item.requests.UpdateDrivingLicenseTypeRequest;

import java.util.List;

public interface DrivingLicenseTypeService {

    void create(CreateDrivingLicenseTypeRequest createDrivingLicenseTypeRequest);

    DrivingLicenseTypeDTO update(UpdateDrivingLicenseTypeRequest updateDrivingLicenseTypeRequest);

    DrivingLicenseTypeDTO getById(int id);

    List<DrivingLicenseTypeDTO> getAll();

    List<DrivingLicenseTypeDTO> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);
}
