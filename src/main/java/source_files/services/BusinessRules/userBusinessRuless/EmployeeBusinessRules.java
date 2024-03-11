package source_files.services.BusinessRules.userBusinessRuless;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.userRequests.CreateEmployeeRequest;
import source_files.data.requests.userRequests.UpdateEmployeeRequest;
import source_files.dataAccess.userRepositories.EmployeeRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.exception.ValidationException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseUserBusinessRulesService;
import source_files.services.entityServices.userEntityManagers.EmployeeEntityServiceImpl;

import java.util.List;

import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.EMAIL_ADDRESS_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.PHONE_NUMBER_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.EMPLOYEE_LIST_NOT_FOUND;
import static source_files.exception.exceptionTypes.ValidationExceptionType.VALIDATION_EXCEPTION;

@RequiredArgsConstructor
@Service
public class EmployeeBusinessRules implements BaseUserBusinessRulesService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeEntityServiceImpl employeeEntityServiceImpl;


    //--------------------- AUTO FIX METHODS ---------------------

    public CreateEmployeeRequest fixCreateEmployeeRequest(CreateEmployeeRequest createEmployeeRequest) {
        createEmployeeRequest.setPhoneNumber(this.fixName(createEmployeeRequest.getPhoneNumber()));
        createEmployeeRequest.setName(this.fixName(createEmployeeRequest.getName()));
        createEmployeeRequest.setSurname(this.fixName(createEmployeeRequest.getSurname()));
        createEmployeeRequest.setEmailAddress(this.fixName(createEmployeeRequest.getEmailAddress()));
        return createEmployeeRequest;
    }

    public UpdateEmployeeRequest fixUpdateEmployeeRequest(UpdateEmployeeRequest updateEmployeeRequest) {
        updateEmployeeRequest.setPhoneNumber(this.fixName(updateEmployeeRequest.getPhoneNumber()));
        updateEmployeeRequest.setName(this.fixName(updateEmployeeRequest.getName()));
        updateEmployeeRequest.setSurname(this.fixName(updateEmployeeRequest.getSurname()));
        updateEmployeeRequest.setEmailAddress(this.fixName(updateEmployeeRequest.getEmailAddress()));
        return updateEmployeeRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public void checkUpdateEmployeeRequest(UpdateEmployeeRequest updateEmployeeRequest) {
        this.existsByEmailAddress(updateEmployeeRequest.getEmailAddress());
        this.existsByPhoneNumber(updateEmployeeRequest.getPhoneNumber());
    }

    public void checkCreateEmployeeRequest(CreateEmployeeRequest createEmployeeRequest) {
        this.checkSalary(createEmployeeRequest.getSalary());
        this.existsByEmailAddress(createEmployeeRequest.getEmailAddress());
        this.existsByPhoneNumber(createEmployeeRequest.getPhoneNumber());
    }


    //----------------------------METHODS--------------------------------

    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(EMPLOYEE_LIST_NOT_FOUND);
        }
        return list;
    }

    public void checkSalary(double salary) {
        if (salary <= 0) {
            throw new ValidationException(VALIDATION_EXCEPTION);
        }
    }

    @Override
    public String fixName(String name) {
        return name.trim().toLowerCase();
    }


    @Override
    public void existsByPhoneNumber(String phoneNumber) {
        if (employeeRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistsException(PHONE_NUMBER_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByEmailAddress(String email) {
        if (employeeRepository.existsByEmailAddress(email)) {
            throw new AlreadyExistsException(EMAIL_ADDRESS_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByEmailAddressAndIdNot(String emailAddress, int id) {
        //Kendisi hariç başka bir email ile aynı olup olmadığını kontrol etmek için
        if (employeeRepository.existsByEmailAddressAndIdNot(emailAddress, id)) {
            throw new AlreadyExistsException(EMAIL_ADDRESS_ALREADY_EXISTS);
        }
    }


    @Override
    public void existsByPhoneNumberAndIdNot(String phoneNumber, int id) {
        if (employeeRepository.existsByPhoneNumberAndIdNot(phoneNumber, id)) {
            throw new AlreadyExistsException(PHONE_NUMBER_ALREADY_EXISTS);
        }
    }
}
