package src.repository.user.admin;

import src.controller.user.admin.request.CreateAdminRequest;
import src.controller.user.admin.request.UpdateAdminRequest;

import java.util.List;

public interface AdminEntityService {

    AdminEntity create(CreateAdminRequest createAdminRequest);

    AdminEntity update(UpdateAdminRequest updateAdminRequest);

    AdminEntity update(AdminEntity adminEntity);

    AdminEntity getById(int id);

    AdminEntity getByEmailAddress(String emailAddress);

    List<AdminEntity> getAll();

    List<AdminEntity> getAllByDeletedState(boolean isDeleted);

    void delete(AdminEntity adminEntity);

    int getCountByDeletedState(boolean isDeleted);

}
