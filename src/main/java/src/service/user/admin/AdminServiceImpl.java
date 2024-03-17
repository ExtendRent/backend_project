package src.service.user.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import src.controller.user.admin.requests.CreateAdminRequest;
import src.controller.user.admin.requests.UpdateAdminRequest;
import src.controller.user.admin.responses.AdminResponse;
import src.repository.image.UserImageEntity;
import src.repository.user.admin.AdminEntity;
import src.repository.user.admin.AdminEntityService;
import src.service.image.user.UserImageService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AdminEntityService entityService;
    private final AdminRules rules;
    private final PasswordEncoder passwordEncoder;
    private final UserImageService userImageService;

    @Override
    public void create(CreateAdminRequest createAdminRequest) {
        createAdminRequest = rules.fix(createAdminRequest);

        try {
            rules.check(createAdminRequest);
            createAdminRequest.setPassword(passwordEncoder.encode(createAdminRequest.getPassword()));
            this.entityService.create(createAdminRequest);
        } catch (Exception e) {
            userImageService.delete(createAdminRequest.getUserImageEntityId());
            throw e;
        }
    }

    @Override
    public AdminResponse update(UpdateAdminRequest updateAdminRequest) {
        updateAdminRequest = rules.fix(updateAdminRequest);
        rules.check(updateAdminRequest);
        updateAdminRequest.setPassword(passwordEncoder.encode(updateAdminRequest.getPassword()));
        UserImageEntity userImage = entityService.getById(updateAdminRequest.getId()).getUserImageEntity();
        if (userImage.getId() != updateAdminRequest.getUserImageEntityId()) {
            userImageService.delete(userImage.getId());
        }
        return entityService.update(updateAdminRequest).toModel();
    }

    @Override
    public AdminResponse getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<AdminResponse> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public AdminResponse getByEmailAddress(String emailAddress) {
        return entityService.getByEmailAddress(emailAddress).toModel();
    }

    @Override
    public List<AdminResponse> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
    }

    @Override
    public void delete(int id, boolean hardDelete) {

        if (hardDelete) {
            AdminEntity adminEntity = entityService.getById(id);
            this.entityService.delete(adminEntity);
            userImageService.delete(adminEntity.getUserImageEntity().getId());
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        AdminEntity adminEntity = entityService.getById(id);
        adminEntity.setIsDeleted(true);
        adminEntity.setDeletedAt(LocalDateTime.now());
        this.entityService.update(adminEntity);
    }

    @Override
    public int getCountByDeletedState(boolean isDeleted) {
        return entityService.getCountByDeletedState(isDeleted);
    }

    private List<AdminResponse> mapToDTOList(List<AdminEntity> adminEntityList) {
        rules.checkDataList(adminEntityList);
        return adminEntityList.stream().map(AdminEntity::toModel).toList();
    }
}
