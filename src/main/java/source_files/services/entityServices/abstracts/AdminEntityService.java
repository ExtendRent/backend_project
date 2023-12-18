package source_files.services.entityServices.abstracts;

import source_files.data.models.userEntities.AdminEntity;

import java.util.List;

public interface AdminEntityService {

    AdminEntity add(AdminEntity adminEntity);

    AdminEntity update(AdminEntity adminEntity);

    void delete(AdminEntity adminEntity);

    AdminEntity getById(int id);

    List<AdminEntity> getAll();

}
