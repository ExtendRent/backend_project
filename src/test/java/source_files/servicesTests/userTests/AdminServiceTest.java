package source_files.servicesTests.userTests;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
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
import java.util.stream.Collectors;

import static source_files.data.enums.defaultDataEnums.Status.DefaultUserStatus.VERIFIED;

@Service
@RequiredArgsConstructor
public class AdminServiceTest implements AdminService {

    private final AdminEntityService entityService;
    private final ModelMapperService mapper;
    private final AdminBusinessRules rules;
    private final PasswordEncoder passwordEncoder;
    private final UserImageService userImageService;

    @Override
    public void create(CreateAdminRequest createAdminRequest) {

        try {
            AdminEntity adminEntity = mapper.forRequest()
                    .map(rules.checkCreateAdminRequest
                            (rules.fixCreateAdminRequest(createAdminRequest)), AdminEntity.class);

            adminEntity.setPassword(passwordEncoder.encode(createAdminRequest.getPassword()));
            adminEntity.setStatus(VERIFIED);
            this.entityService.create(adminEntity);
        } catch (Exception e) {
            userImageService.delete(createAdminRequest.getUserImageEntityId());
            throw e;
        }

    }

    @Override
    public AdminDTO update(UpdateAdminRequest updateAdminRequest) {
        AdminEntity adminEntity = entityService.getById(updateAdminRequest.getId());
        UserImageEntity userImage = adminEntity.getUserImageEntity();
        if (userImage.getId() != updateAdminRequest.getUserImageEntityId()) {
            userImageService.delete(userImage.getId());
        }
        adminEntity = mapper.forRequest()
                .map(rules.checkUpdateAdminRequest
                        (rules.fixUpdateAdminRequest(updateAdminRequest)), AdminEntity.class);

        return mapper.forResponse().map(this.entityService.create(adminEntity), AdminDTO.class);
    }

    @Override
    public AdminDTO getById(int id) {
        return this.mapper.forResponse().map(entityService.getById(id), AdminDTO.class);
    }

    @Override
    public List<AdminDTO> getAll() {
        rules.checkDataList(entityService.getAll());
        return entityService.getAll()
                .stream().map(admin -> mapper.forResponse()
                        .map(admin, AdminDTO.class)).collect(Collectors.toList());
    }

    @Override
    public AdminDTO getByEmailAddress(String emailAddress) {
        return this.mapper.forResponse()
                .map(entityService.getByEmailAddress(emailAddress), AdminDTO.class);
    }

    @Override
    public List<AdminDTO> getAllByDeletedState(boolean isDeleted) {
        return rules.checkDataList(entityService.getAllByDeletedState(isDeleted)).stream()
                .map(admin -> mapper.forResponse().map(admin, AdminDTO.class)).toList();
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

}
