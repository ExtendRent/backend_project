package src.service.user.admin;

import src.controller.user.admin.request.CreateAdminRequest;
import src.controller.user.admin.request.UpdateAdminRequest;
import src.controller.user.admin.response.AdminResponse;

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
