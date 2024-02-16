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

    private final AdminRepository adminRepository;
    private final AdminBusinessRules adminBusinessRules;

    @Override
    public AdminEntity create(AdminEntity adminEntity) {

        return this.adminRepository.save(adminEntity);
    }

    @Override
    public AdminEntity update(AdminEntity adminEntity) {

        return this.create(adminEntity);
    }

    @Override
    public AdminEntity getById(int id) {
        return this.adminRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ADMIN_DATA_NOT_FOUND, "Admin bulunamadı"));
    }

    @Override
    public List<AdminEntity> getAll() {

        return this.adminBusinessRules.checkDataList(this.adminRepository.findAll());
    }

    @Override
    public List<AdminEntity> getAllByDeletedState(boolean isDeleted) {
        return this.adminRepository.findAllByIsDeleted(isDeleted);
    }


    @Override
    public void delete(AdminEntity adminEntity) {

        this.adminRepository.delete(adminEntity);
    }

    @Override
    public AdminEntity getByEmailAddress(String emailAddress) {
        return this.adminRepository.findByEmailAddress(emailAddress).orElseThrow(
                () -> new DataNotFoundException(ADMIN_DATA_NOT_FOUND, "Bu email adresine kayıtlı admin bulunamadı")
        );
    }

}
