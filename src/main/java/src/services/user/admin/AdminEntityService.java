package src.services.user.admin;

import src.controllers.user.requests.admin.CreateAdminRequest;
import src.controllers.user.requests.admin.UpdateAdminRequest;
import src.data.models.user_entities.AdminEntity;

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
