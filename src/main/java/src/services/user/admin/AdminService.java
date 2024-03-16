package src.services.user.admin;

import src.controllers.user.requests.admin.CreateAdminRequest;
import src.controllers.user.requests.admin.UpdateAdminRequest;
import src.controllers.user.responses.AdminResponse;

import java.util.List;

public interface AdminService {

    void create(CreateAdminRequest createAdminRequest);

    AdminResponse update(UpdateAdminRequest updateAdminRequest);

    AdminResponse getById(int id);

    List<AdminResponse> getAll();

    AdminResponse getByEmailAddress(String emailAddress);

    List<AdminResponse> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);

    int getCountByDeletedState(boolean isDeleted);

}
