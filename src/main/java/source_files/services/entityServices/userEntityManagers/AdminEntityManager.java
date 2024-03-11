package source_files.services.entityServices.userEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.userEntities.AdminEntity;
import source_files.data.requests.userRequests.CreateAdminRequest;
import source_files.data.requests.userRequests.UpdateAdminRequest;
import source_files.dataAccess.userRepositories.AdminRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.userBusinessRuless.AdminBusinessRules;
import source_files.services.entityServices.abstracts.userAbstract.AdminEntityService;
import source_files.services.systemServices.ImageServices.UserImageService;

import java.util.List;

import static source_files.data.enums.defaultDataEnums.Status.DefaultUserStatus.VERIFIED;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.ADMIN_DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class AdminEntityManager implements AdminEntityService {

    private final AdminRepository repository;
    private final AdminBusinessRules adminBusinessRules;
    private final UserImageService userImageService;

    @Override
    public AdminEntity create(CreateAdminRequest createAdminRequest) {
        AdminEntity adminEntity = AdminEntity.adminBuilder()
                .name(createAdminRequest.getName())
                .surname(createAdminRequest.getSurname())
                .emailAddress(createAdminRequest.getEmailAddress())
                .password(createAdminRequest.getPassword())
                .salary(createAdminRequest.getSalary())
                .phoneNumber(createAdminRequest.getPhoneNumber())
                .userImageEntity(userImageService.getById(createAdminRequest.getUserImageEntityId()))
                .status(VERIFIED)
                .build();
        return repository.save(adminEntity);
    }

    @Override
    public AdminEntity update(UpdateAdminRequest updateAdminRequest) {
        AdminEntity adminEntity = AdminEntity.adminBuilder()
                .id(updateAdminRequest.getId())
                .name(updateAdminRequest.getName())
                .surname(updateAdminRequest.getSurname())
                .emailAddress(updateAdminRequest.getEmailAddress())
                .password(updateAdminRequest.getPassword())
                .salary(updateAdminRequest.getSalary())
                .phoneNumber(updateAdminRequest.getPhoneNumber())
                .status(updateAdminRequest.getStatus())
                .userImageEntity(userImageService.getById(updateAdminRequest.getUserImageEntityId()))
                .build();
        return repository.save(adminEntity);
    }

    @Override
    public AdminEntity update(AdminEntity adminEntity) {
        return repository.save(adminEntity);
    }

    @Override
    public AdminEntity getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(ADMIN_DATA_NOT_FOUND));
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
                () -> new DataNotFoundException(ADMIN_DATA_NOT_FOUND)
        );
    }

}
