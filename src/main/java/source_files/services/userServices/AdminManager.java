package source_files.services.userServices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import source_files.data.DTO.userDTOs.AdminDTO;
import source_files.data.models.imageEntities.UserImageEntity;
import source_files.data.models.userEntities.AdminEntity;
import source_files.data.requests.userRequests.CreateAdminRequest;
import source_files.data.requests.userRequests.UpdateAdminRequest;
import source_files.services.BusinessRules.userBusinessRuless.AdminBusinessRules;
import source_files.services.entityServices.abstracts.userAbstract.AdminEntityService;
import source_files.services.systemServices.ImageServices.UserImageService;
import source_files.services.userServices.abstracts.AdminService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminManager implements AdminService {

    private final AdminEntityService entityService;
    private final AdminBusinessRules rules;
    private final PasswordEncoder passwordEncoder;
    private final UserImageService userImageService;

    @Override
    public void create(CreateAdminRequest createAdminRequest) {
        createAdminRequest = rules.fixCreateAdminRequest(createAdminRequest);

        try {
            rules.checkCreateAdminRequest(createAdminRequest);
            createAdminRequest.setPassword(passwordEncoder.encode(createAdminRequest.getPassword()));
            this.entityService.create(createAdminRequest);
        } catch (Exception e) {
            userImageService.delete(createAdminRequest.getUserImageEntityId());
            throw e;
        }
    }

    @Override
    public AdminDTO update(UpdateAdminRequest updateAdminRequest) {
        updateAdminRequest = rules.fixUpdateAdminRequest(updateAdminRequest);
        rules.checkUpdateAdminRequest(updateAdminRequest);
        UserImageEntity userImage = entityService.getById(updateAdminRequest.getId()).getUserImageEntity();

        if (userImage.getId() != updateAdminRequest.getUserImageEntityId()) {
            userImageService.delete(userImage.getId());
        }
        return entityService.update(updateAdminRequest).toModel();
    }

    @Override
    public AdminDTO getById(int id) {
        return entityService.getById(id).toModel();
    }

    @Override
    public List<AdminDTO> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public AdminDTO getByEmailAddress(String emailAddress) {
        return entityService.getByEmailAddress(emailAddress).toModel();
    }

    @Override
    public List<AdminDTO> getAllByDeletedState(boolean isDeleted) {
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

    private List<AdminDTO> mapToDTOList(List<AdminEntity> adminEntityList) {
        rules.checkDataList(adminEntityList);
        return adminEntityList.stream().map(AdminEntity::toModel).toList();
    }
}
