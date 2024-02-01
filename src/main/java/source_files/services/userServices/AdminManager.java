package source_files.services.userServices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.userDTOs.AdminDTO;
import source_files.data.models.userEntities.AdminEntity;
import source_files.data.requests.userRequests.CreateAdminRequest;
import source_files.data.requests.userRequests.UpdateAdminRequest;
import source_files.services.BusinessRules.userBusinessRuless.AdminBusinessRules;
import source_files.services.entityServices.abstracts.userAbstract.AdminEntityService;
import source_files.services.userServices.abstracts.AdminService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static source_files.data.Status.DefaultUserStatus.VERIFIED;
import static source_files.data.types.userTypes.UserRole.ADMIN;

@Service
@RequiredArgsConstructor
public class AdminManager implements AdminService {

    private final AdminEntityService adminEntityService;
    private final ModelMapperService modelMapperService;
    private final AdminBusinessRules adminBusinessRules;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(CreateAdminRequest createAdminRequest) {

        AdminEntity adminEntity = modelMapperService.forRequest()
                .map(adminBusinessRules.checkCreateAdminRequest
                        (adminBusinessRules.fixCreateAdminRequest(createAdminRequest)), AdminEntity.class);

        adminEntity.setPassword(passwordEncoder.encode(createAdminRequest.getPassword()));
        adminEntity.setAuthority(ADMIN);
        adminEntity.setStatus(VERIFIED);
        this.adminEntityService.create(adminEntity);
    }

    @Override
    public AdminDTO update(UpdateAdminRequest updateAdminRequest) {
        AdminEntity adminEntity = modelMapperService.forRequest()
                .map(adminBusinessRules.checkUpdateAdminRequest
                        (adminBusinessRules.fixUpdateAdminRequest(updateAdminRequest)), AdminEntity.class);

        return modelMapperService.forResponse().map(this.adminEntityService.create(adminEntity), AdminDTO.class);
    }

    @Override
    public AdminDTO getById(int id) {

        return this.modelMapperService.forResponse().map(adminEntityService.getById(id), AdminDTO.class);
    }

    @Override
    public List<AdminDTO> getAll() {

        return adminEntityService.getAll()
                .stream().map(admin -> modelMapperService.forResponse()
                        .map(admin, AdminDTO.class)).collect(Collectors.toList());
    }


    @Override
    public List<AdminDTO> getAllBySalaryGreaterThanEqual(Double salary) {
        return this.adminBusinessRules.checkDataList(this.adminEntityService.getAllBySalaryGreaterThanEqual(salary))
                .stream().map(adminEntity -> modelMapperService.forResponse().map(adminEntity, AdminDTO.class)).toList();
    }


    @Override
    public AdminDTO getByPhoneNumber(String phoneNumber) {

        return this.modelMapperService.forResponse().map(adminEntityService.getByPhoneNumber(phoneNumber), AdminDTO.class);
    }

    @Override
    public AdminDTO getByEmailAddress(String emailAddress) {
        return this.modelMapperService.forResponse()
                .map(adminEntityService.getByEmailAddress(emailAddress), AdminDTO.class);
    }

    @Override
    public List<AdminDTO> getAllByDeletedState(boolean isDeleted) {
        return adminEntityService.getAllByDeletedState(isDeleted).stream()
                .map(admin -> modelMapperService.forResponse().map(admin, AdminDTO.class)).toList();
    }

    @Override
    public void delete(int id, boolean hardDelete) {

        if (hardDelete) {
            this.adminEntityService.delete(adminEntityService.getById(id));
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        AdminEntity adminEntity = adminEntityService.getById(id);
        adminEntity.setIsDeleted(true);
        adminEntity.setDeletedAt(LocalDateTime.now());
        this.adminEntityService.update(adminEntity);
    }

}
