package source_files.services.entityServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.userEntities.AdminEntity;
import source_files.services.entityServices.abstracts.AdminEntityService;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminEntityManager implements AdminEntityService {
    @Override
    public AdminEntity add(AdminEntity adminEntity) {
        return null;
    }

    @Override
    public AdminEntity update(AdminEntity adminEntity) {
        return null;
    }

    @Override
    public AdminEntity getById(int id) {
        return null;
    }

    @Override
    public List<AdminEntity> getAll() {
        return null;
    }

    @Override
    public List<AdminEntity> getAllByIsDeletedFalse() {
        return null;
    }

    @Override
    public List<AdminEntity> getAllByIsDeletedTrue() {
        return null;
    }

    @Override
    public void delete(AdminEntity adminEntity) {

    }
}
