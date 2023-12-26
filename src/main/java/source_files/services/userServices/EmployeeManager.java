package source_files.services.userServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.userDTOs.EmployeeDTO;
import source_files.data.models.userEntities.EmployeeEntity;
import source_files.data.requests.userRequests.AddEmployeeRequest;
import source_files.data.requests.userRequests.UpdateEmployeeRequest;
import source_files.data.types.UserType;
import source_files.services.BusinessRules.EmployeeBusinessRules;
import source_files.services.entityServices.abstracts.EmployeeEntityService;
import source_files.services.userServices.abstracts.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {

    private final EmployeeEntityService employeeEntityService;
    private final ModelMapperService modelMapperService;
    private final EmployeeBusinessRules employeeBusinessRules;


    @Override
    public EmployeeDTO add(AddEmployeeRequest addEmployeeRequest) {
        EmployeeEntity employeeEntity = modelMapperService.forRequest()
                .map(employeeBusinessRules.checkAddEmployeeRequest
                        (employeeBusinessRules.fixAddEmployeeRequest(addEmployeeRequest)), EmployeeEntity.class);
        employeeEntity.setUserType(UserType.EMPLOYEE);
        return modelMapperService.forResponse().map(this.employeeEntityService.add(employeeEntity), EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO update(UpdateEmployeeRequest updateEmployeeRequest) {
        EmployeeEntity employeeEntity = modelMapperService.forRequest()
                .map(employeeBusinessRules.checkUpdateEmployeeRequest
                        (employeeBusinessRules.fixUpdateEmployeeRequest(updateEmployeeRequest)), EmployeeEntity.class);
        employeeEntity.setUserType(UserType.EMPLOYEE);
        return modelMapperService.forResponse().map(this.employeeEntityService.add(employeeEntity), EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getById(int id) {
        return this.modelMapperService.forResponse().map(employeeEntityService.getById(id), EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return employeeBusinessRules.checkDataList(employeeEntityService.getAll())
                .stream().map(employeeEntity -> modelMapperService.forResponse()
                        .map(employeeEntity, EmployeeDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDTO> findAllBySalaryBetween(double salary1, double salary2) {
        return this.employeeBusinessRules.checkDataList(this.employeeEntityService.getAllBySalaryBetween(salary1, salary2))
                .stream().map(employeeEntity -> modelMapperService.forResponse().map(employeeEntity, EmployeeDTO.class)).toList();
    }

    @Override
    public List<EmployeeDTO> getAllByIsDeletedFalse() {
        return this.employeeBusinessRules.checkDataList(this.employeeEntityService.getAllByIsDeletedFalse())
                .stream().map(employeeEntity -> modelMapperService.forResponse().map(employeeEntity, EmployeeDTO.class)).toList();
    }

    @Override
    public List<EmployeeDTO> getAllByIsDeletedTrue() {
        return this.employeeBusinessRules.checkDataList(this.employeeEntityService.getAllByIsDeletedTrue())
                .stream().map(employeeEntity -> modelMapperService.forResponse().map(employeeEntity, EmployeeDTO.class)).toList();
    }

    @Override
    public EmployeeDTO getByPhoneNumber(String phoneNumber) {
        return this.modelMapperService.forResponse().map(employeeEntityService.getByPhoneNumber(phoneNumber), EmployeeDTO.class);
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
        this.employeeEntityService.delete(employeeEntityService.getById(id));
    }

    @Override
    public void softDelete(int id) {
        EmployeeEntity employeeEntity = employeeEntityService.getById(id);
        employeeEntity.setIsDeleted(true);
        this.employeeEntityService.update(employeeEntity);
    }
}
