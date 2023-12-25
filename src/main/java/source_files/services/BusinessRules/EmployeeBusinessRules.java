package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.userRequests.AddEmployeeRequest;
import source_files.data.requests.userRequests.UpdateEmployeeRequest;
import source_files.dataAccess.userRepositories.EmployeeRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.EmployeeEntityManager;


import java.util.List;

import static source_files.exception.AlreadyExistsExceptionType.EMAIL_ADDRESS_ALREADY_EXISTS;
import static source_files.exception.AlreadyExistsExceptionType.PHONE_NUMBER_ALREADY_EXISTS;
import static source_files.exception.NotFoundExceptionType.EMPLOYEE_LIST_NOT_FOUND;

@AllArgsConstructor
@Service
public class EmployeeBusinessRules implements BaseBusinessRulesService{
    private final EmployeeRepository employeeRepository;
    private final EmployeeEntityManager employeeEntityManager;

    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()){
            throw new DataNotFoundException(EMPLOYEE_LIST_NOT_FOUND,"Aradığınız kriterlere uygun çalışan bulunamadı");
        }
        return list;
    }

    public AddEmployeeRequest checkAddEmployeeRequest (AddEmployeeRequest addEmployeeRequest) {
        this.existsByEmailAddress(addEmployeeRequest.getEmailAddress());
        this.existsByPhoneNumber(addEmployeeRequest.getPhoneNumber());
        return addEmployeeRequest;
    }
    public AddEmployeeRequest fixAddEmployeeRequest(AddEmployeeRequest addEmployeeRequest){
        addEmployeeRequest.setPhoneNumber(this.fixName(addEmployeeRequest.getPhoneNumber()));
        addEmployeeRequest.setName(this.fixName(addEmployeeRequest.getName()));
        addEmployeeRequest.setSurname(this.fixName(addEmployeeRequest.getSurname()));
        addEmployeeRequest.setEmailAddress(this.fixName(addEmployeeRequest.getEmailAddress()));
        return addEmployeeRequest;
    }

    public UpdateEmployeeRequest checkUpdateEmployeeRequest (UpdateEmployeeRequest updateEmployeeRequest) {
        this.existsByEmailAddress(updateEmployeeRequest.getEmailAddress());
        this.existsByPhoneNumber(updateEmployeeRequest.getPhoneNumber());
        updateEmployeeRequest.setId(this.employeeEntityManager.getById(updateEmployeeRequest.getId()).getId());
        return updateEmployeeRequest ;
    }

    public UpdateEmployeeRequest fixUpdateEmployeeRequest(UpdateEmployeeRequest updateEmployeeRequest){
        updateEmployeeRequest.setPhoneNumber(this.fixName(updateEmployeeRequest.getPhoneNumber()));
        updateEmployeeRequest.setName(this.fixName(updateEmployeeRequest.getName()));
        updateEmployeeRequest.setSurname(this.fixName(updateEmployeeRequest.getSurname()));
        updateEmployeeRequest.setEmailAddress(this.fixName(updateEmployeeRequest.getEmailAddress()));
        return updateEmployeeRequest;
    }

    @Override
    public String fixName(String name) {
        return name.trim().toLowerCase();
    }

    //---------------AUTO CHECKING METHODS--------------------------------
    private void existsByPhoneNumber(String phoneNumber) {
        if (employeeRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistsException(PHONE_NUMBER_ALREADY_EXISTS, "This phone number already exist");
        }
    }
    public void existsByEmailAddress(String email){
        if (employeeRepository.existsByEmailAddress(email)){
            throw new AlreadyExistsException(EMAIL_ADDRESS_ALREADY_EXISTS,"This email address already exist");
        }
    }
}
