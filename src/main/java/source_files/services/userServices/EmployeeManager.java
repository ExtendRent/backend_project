package source_files.services.userServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.userDTOs.EmployeeDTO;
import source_files.data.models.userEntities.EmployeeEntity;
import source_files.services.entityServices.abstracts.EmployeeEntityService;
import source_files.services.userServices.abstracts.EmployeeService;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {

    private final EmployeeEntityService employeeEntityService;
    private ModelMapperService modelMapperService;


    @Override
    public EmployeeDTO add(EmployeeEntity employeeEntity) {
        //TODO Employee için addEmployeeRequest yazılcak
        return null;
    }

    @Override
    public EmployeeDTO getById(int id) {
        return this.modelMapperService.forResponse().map(this.employeeEntityService.getById(id), EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return this.employeeEntityService.getAll().stream()
                .map(employeeEntity -> modelMapperService.forResponse().map(employeeEntity, EmployeeDTO.class)).toList();
    }

    @Override
    public List<EmployeeDTO> getAllByIsDeletedFalse() {
        return this.employeeEntityService.getAllByIsDeletedFalse()
                .stream().map(employeeEntity -> modelMapperService.forResponse().map(employeeEntity, EmployeeDTO.class)).toList();
    }

    @Override
    public List<EmployeeDTO> getAllByIsDeletedTrue() {
        return this.employeeEntityService.getAllByIsDeletedTrue()
                .stream().map(employeeEntity -> modelMapperService.forResponse().map(employeeEntity, EmployeeDTO.class)).toList();
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
        this.employeeEntityService.delete(this.employeeEntityService.getById(id));
    }

    @Override
    public void softDelete(int id) {
        EmployeeEntity employeeEntity = this.employeeEntityService.getById(id);
        employeeEntity.setIsDeleted(true);
        this.employeeEntityService.update(employeeEntity);
    }
}
