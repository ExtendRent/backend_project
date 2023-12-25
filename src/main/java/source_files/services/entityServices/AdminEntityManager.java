package source_files.services.entityServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.userEntities.AdminEntity;
import source_files.dataAccess.userRepositories.AdminRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.AdminEntityService;

import java.util.List;

import static source_files.exception.NotFoundExceptionType.ADMIN_DATA_NOT_FOUND;

@Service
@AllArgsConstructor
public class AdminEntityManager implements AdminEntityService {

    private final AdminRepository adminRepository;

    @Override
    public AdminEntity add(AdminEntity adminEntity) {

        return this.adminRepository.save(adminEntity);
    }

    @Override
    public AdminEntity update(AdminEntity adminEntity) {

        return this.add(adminEntity);
    }

    @Override
    public AdminEntity getById(int id) {
        return this.adminRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ADMIN_DATA_NOT_FOUND, "Admin bulunamadı"));
    }

    @Override
    public List<AdminEntity> getAll() {

        return this.adminRepository.findAll();
    }

    @Override
    public List<AdminEntity> getAllBySalaryGreaterThanEqual(Double salary) {
        return this.adminRepository.findBySalaryGreaterThanEqual(salary);
    }


    @Override
    public List<AdminEntity> getAllByIsDeletedFalse() {

        return this.adminRepository.findAllByIsDeletedFalse();
    }

    @Override
    public List<AdminEntity> getAllByIsDeletedTrue() {

        return this.adminRepository.findAllByIsDeletedTrue();
    }

    @Override
    public void delete(AdminEntity adminEntity) {

        this.adminRepository.delete(adminEntity);
    }

    @Override
    public AdminEntity getByPhoneNumber(String phoneNumber) {
        return this.adminRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new DataNotFoundException(ADMIN_DATA_NOT_FOUND, "Bu telefon numarasına kayıtlı admin bulunamadı")
        );
    }

    @Override
    public void hardDelete(int id) {
        this.adminRepository.delete(adminRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException(ADMIN_DATA_NOT_FOUND, "Bu admin sistemde bulunamadı")

        ));
    }

    @Override
    public void softDelete(int id) {
        AdminEntity adminEntity = adminRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException(ADMIN_DATA_NOT_FOUND, "Bu admin sistemde bulunamadı"));
        adminEntity.setIsDeleted(true);
        this.add(adminEntity);
    }
}
