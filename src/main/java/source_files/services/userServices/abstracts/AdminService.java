package source_files.services.userServices.abstracts;

import source_files.data.DTO.userDTOs.AdminDTO;
import source_files.data.models.userEntities.AdminEntity;

import java.util.List;

public interface AdminService {

    AdminDTO add(AdminEntity adminEntity);

    AdminDTO getById(int id);

    List<AdminDTO> getAll();

    List<AdminDTO> getAllByIsDeletedFalse();

    List<AdminDTO> getAllByIsDeletedTrue();

    void delete(int id, boolean hardDelete);

    void hardDelete(int id);

    void softDelete(int id);


}
