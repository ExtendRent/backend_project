package source_files.services.userServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.userDTOs.AdminDTO;
import source_files.data.models.userEntities.AdminEntity;
import source_files.data.requests.userRequests.AddAdminRequest;
import source_files.data.requests.userRequests.UpdateAdminRequest;
import source_files.data.types.UserType;
import source_files.services.BusinessRules.AdminBusinessRules;
import source_files.services.entityServices.abstracts.AdminEntityService;
import source_files.services.userServices.abstracts.AdminService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminManager implements AdminService {

    private final AdminEntityService adminEntityService;
    private final ModelMapperService modelMapperService;
    private final AdminBusinessRules adminBusinessRules;


    @Override
    public AdminDTO add(AddAdminRequest addAdminRequest) {
        AdminEntity adminEntity = modelMapperService.forRequest()
                .map(adminBusinessRules.checkAddAdminRequest
                        (adminBusinessRules.fixAddAdminRequest(addAdminRequest)), AdminEntity.class);
        adminEntity.setUserType(UserType.ADMIN);
        return modelMapperService.forResponse().map(this.adminEntityService.add(adminEntity), AdminDTO.class);
    }

    @Override
    public AdminDTO update(UpdateAdminRequest updateAdminRequest) {
        AdminEntity adminEntity = modelMapperService.forRequest()
                .map(adminBusinessRules.checkUpdateAdminRequest
                        (adminBusinessRules.fixUpdateAdminRequest(updateAdminRequest)), AdminEntity.class);
        adminEntity.setUserType(UserType.ADMIN);
        return modelMapperService.forResponse().map(this.adminEntityService.add(adminEntity), AdminDTO.class);

    }

    @Override
    public AdminDTO getById(int id) {

        return this.modelMapperService.forResponse().map(adminEntityService.getById(id), AdminDTO.class);
    }

    @Override
    public List<AdminDTO> getAll() {

        return adminBusinessRules.checkDataList(adminEntityService.getAll())
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
    public List<AdminDTO> getAllByIsDeletedFalse() {
        return this.adminBusinessRules.checkDataList(this.adminEntityService.getAllByIsDeletedFalse())
                .stream().map(adminEntity -> modelMapperService.forResponse().map(adminEntity, AdminDTO.class)).toList();
    }


    @Override
    public List<AdminDTO> getAllByIsDeletedTrue() {
        return this.adminBusinessRules.checkDataList(this.adminEntityService.getAllByIsDeletedTrue())
                .stream().map(adminEntity -> modelMapperService.forResponse().map(adminEntity, AdminDTO.class)).toList();
    }

    @Override
    public void delete(int id, boolean hardDelete) {

        if (hardDelete) {
            this.hardDelete(id);
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void hardDelete(int id) {
        this.adminEntityService.delete(adminEntityService.getById(id));
    }

    @Override
    public void softDelete(int id) {
        AdminEntity adminEntity = adminEntityService.getById(id);
        adminEntity.setIsDeleted(true);
        this.adminEntityService.update(adminEntity);
    }
}
