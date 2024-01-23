package source_files.services.entityServices.abstracts.userAbstract;

import source_files.data.models.userEntities.AdminEntity;

import java.util.List;

public interface AdminEntityService {

    AdminEntity create(AdminEntity adminEntity);

    AdminEntity update(AdminEntity adminEntity);

    AdminEntity getById(int id);

    AdminEntity getByPhoneNumber(String phoneNumber);

    AdminEntity getByEmailAddress(String emailAddress);

    List<AdminEntity> getAll();

    List<AdminEntity> getAllBySalaryGreaterThanEqual(Double salary);

    List<AdminEntity> getAllByDeletedState(boolean isDeleted);

    void delete(AdminEntity adminEntity);


}
