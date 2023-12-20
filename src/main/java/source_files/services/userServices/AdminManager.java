package source_files.services.userServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.userDTOs.AdminDTO;
import source_files.data.models.userEntities.AdminEntity;
import source_files.services.entityServices.abstracts.AdminEntityService;
import source_files.services.userServices.abstracts.AdminService;

import java.util.List;

@Service
@AllArgsConstructor
public class AdminManager implements AdminService {

    private final AdminEntityService adminEntityService;

    private ModelMapperService modelMapperService;

    @Override
    public AdminDTO add(AdminEntity adminEntity) {
        return null;
    }

    @Override
    public AdminDTO getById(int id) {
        return null;
    }

    @Override
    public List<AdminDTO> getAll() {
        return null;
    }

    @Override
    public List<AdminDTO> getAllByIsDeletedFalse() {
        return this.adminEntityService.getAllByIsDeletedFalse()
                .stream().map(adminEntity -> modelMapperService.forResponse().map(adminEntity, AdminDTO.class)).toList();
    }

    @Override
    public List<AdminDTO> getAllByIsDeletedTrue() {
        return this.adminEntityService.getAllByIsDeletedTrue()
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
        this.adminEntityService.delete(this.adminEntityService.getById(id));
    }

    @Override
    public void softDelete(int id) {
        AdminEntity adminEntity = this.adminEntityService.getById(id);
        adminEntity.setIsDeleted(true);
        this.adminEntityService.update(adminEntity);
    }
}
