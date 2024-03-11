package source_files.services.entityServices.abstracts.userAbstract;

import source_files.data.models.userEntities.AdminEntity;
import source_files.data.requests.userRequests.CreateAdminRequest;
import source_files.data.requests.userRequests.UpdateAdminRequest;

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
