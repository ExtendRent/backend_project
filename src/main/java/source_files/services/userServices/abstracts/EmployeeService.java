package source_files.services.userServices.abstracts;

import source_files.data.DTO.userDTOs.EmployeeDTO;
import source_files.data.models.userEntities.EmployeeEntity;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO add(EmployeeEntity employeeEntity);

    EmployeeDTO getById(int id);

    List<EmployeeDTO> getAll();

    List<EmployeeDTO> getAllByIsDeletedFalse();

    List<EmployeeDTO> getAllByIsDeletedTrue();

    void delete(int id, boolean hardDelete);

    void hardDelete(int id);

    void softDelete(int id);
}
