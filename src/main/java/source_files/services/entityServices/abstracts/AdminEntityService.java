package source_files.services.entityServices.abstracts;

import source_files.data.models.userEntities.AdminEntity;

import java.util.List;

public interface AdminEntityService {

    AdminEntity add(AdminEntity adminEntity);

    AdminEntity update(AdminEntity adminEntity);

    AdminEntity getById(int id);

    List<AdminEntity> getAll();

    List<AdminEntity> getAllBySalaryGreaterThanEqual(Double salary);

    List<AdminEntity> getAllByIsDeletedFalse();

    List<AdminEntity> getAllByIsDeletedTrue();

    void delete(AdminEntity adminEntity);

    AdminEntity getByPhoneNumber(String phoneNumber);

}
