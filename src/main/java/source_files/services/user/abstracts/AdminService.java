package source_files.services.user.abstracts;

import source_files.controllers.user.dtos.AdminDTO;
import source_files.controllers.user.requests.admin.CreateAdminRequest;
import source_files.controllers.user.requests.admin.UpdateAdminRequest;

import java.util.List;

public interface AdminService {

    void create(CreateAdminRequest createAdminRequest);

    AdminDTO update(UpdateAdminRequest updateAdminRequest);

    AdminDTO getById(int id);

    List<AdminDTO> getAll();

    AdminDTO getByEmailAddress(String emailAddress);

    List<AdminDTO> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);

    int getCountByDeletedState(boolean isDeleted);

}
