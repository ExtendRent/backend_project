package source_files.services.entityServices.userEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.userEntities.AdminEntity;
import source_files.dataAccess.userRepositories.AdminRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.userBusinessRuless.AdminBusinessRules;
import source_files.services.entityServices.abstracts.userAbstract.AdminEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.ADMIN_DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AdminEntityManager implements AdminEntityService {

    private final AdminRepository repository;
    private final AdminBusinessRules adminBusinessRules;

    @Override
    public AdminEntity create(AdminEntity adminEntity) {
        adminEntity.setId(0);
        return repository.save(adminEntity);
    }

    @Override
    public AdminEntity update(AdminEntity adminEntity) {

        return create(adminEntity);
    }

    @Override
    public AdminEntity getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ADMIN_DATA_NOT_FOUND, "Admin bulunamadı"));
    }

    @Override
    public List<AdminEntity> getAll() {

        return adminBusinessRules.checkDataList(repository.findAll());
    }

    @Override
    public List<AdminEntity> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted);
    }


    @Override
    public void delete(AdminEntity adminEntity) {

        repository.delete(adminEntity);
    }

    @Override
    public int getCountByDeletedState(boolean isDeleted) {
        return repository.countByIsDeleted(isDeleted);
    }

    @Override
    public AdminEntity getByEmailAddress(String emailAddress) {
        return repository.findByEmailAddress(emailAddress).orElseThrow(
                () -> new DataNotFoundException(ADMIN_DATA_NOT_FOUND, "Bu email adresine kayıtlı admin bulunamadı")
        );
    }

}
