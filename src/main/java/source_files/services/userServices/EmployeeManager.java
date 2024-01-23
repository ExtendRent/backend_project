package source_files.services.userServices;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.userDTOs.EmployeeDTO;
import source_files.data.models.userEntities.EmployeeEntity;
import source_files.data.requests.userRequests.CreateEmployeeRequest;
import source_files.data.requests.userRequests.UpdateEmployeeRequest;
import source_files.services.BusinessRules.userBusinessRuless.EmployeeBusinessRules;
import source_files.services.entityServices.abstracts.userAbstract.EmployeeEntityService;
import source_files.services.userServices.abstracts.EmployeeService;

import java.util.List;
import java.util.stream.Collectors;

import static source_files.data.types.userTypes.UserRole.EMPLOYEE;

@Service
@AllArgsConstructor
public class EmployeeManager implements EmployeeService {

    private final EmployeeEntityService employeeEntityService;
    private final ModelMapperService modelMapperService;
    private final EmployeeBusinessRules employeeBusinessRules;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(CreateEmployeeRequest createEmployeeRequest) {
        EmployeeEntity employeeEntity = modelMapperService.forRequest()
                .map(employeeBusinessRules.checkCreateEmployeeRequest
                        (employeeBusinessRules.fixCreateEmployeeRequest(createEmployeeRequest)), EmployeeEntity.class);
        employeeEntity.setPassword(passwordEncoder.encode(createEmployeeRequest.getPassword()));
        employeeEntity.setAuthority(EMPLOYEE);
        this.employeeEntityService.create(employeeEntity);
    }

    @Override
    public EmployeeDTO update(UpdateEmployeeRequest updateEmployeeRequest) {
        EmployeeEntity employeeEntity = modelMapperService.forRequest()
                .map(employeeBusinessRules.checkUpdateEmployeeRequest
                        (employeeBusinessRules.fixUpdateEmployeeRequest(updateEmployeeRequest)), EmployeeEntity.class);
        return modelMapperService.forResponse().map(this.employeeEntityService.create(employeeEntity), EmployeeDTO.class);
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
    public List<EmployeeDTO> getAllBySalaryBetween(double salary1, double salary2) {
        return this.employeeBusinessRules.checkDataList(this.employeeEntityService.getAllBySalaryBetween(salary1, salary2))
                .stream().map(employeeEntity -> modelMapperService.forResponse().map(employeeEntity, EmployeeDTO.class)).toList();
    }

    @Override
    public List<EmployeeDTO> getAllByDeletedState(boolean isDeleted) {
        return this.employeeBusinessRules.checkDataList(this.employeeEntityService.getAllByDeletedState(isDeleted))
                .stream().map(employeeEntity -> modelMapperService.forResponse()
                        .map(employeeEntity, EmployeeDTO.class)).toList();
    }


    @Override
    public EmployeeDTO getByPhoneNumber(String phoneNumber) {
        return this.modelMapperService.forResponse()
                .map(employeeEntityService.getByPhoneNumber(phoneNumber), EmployeeDTO.class);
    }

    @Override
    public EmployeeDTO getByEmailAddress(String emailAddress) {
        return this.modelMapperService.forResponse()
                .map(employeeEntityService.getByEmailAddress(emailAddress), EmployeeDTO.class);
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            this.employeeEntityService.delete(employeeEntityService.getById(id));
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        EmployeeEntity employeeEntity = employeeEntityService.getById(id);
        employeeEntity.setIsDeleted(true);
        this.employeeEntityService.update(employeeEntity);
    }
}
