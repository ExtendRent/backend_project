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
import source_files.services.entityServices.userEntityManagers.EmployeeEntityManager;

import java.util.List;

import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.EMAIL_ADDRESS_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.PHONE_NUMBER_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.EMPLOYEE_LIST_NOT_FOUND;
import static source_files.exception.exceptionTypes.ValidationExceptionType.VALIDATION_EXCEPTION;

@RequiredArgsConstructor
@Service
public class EmployeeBusinessRules implements BaseUserBusinessRulesService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeEntityManager employeeEntityManager;


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
    public UpdateEmployeeRequest checkUpdateEmployeeRequest(UpdateEmployeeRequest updateEmployeeRequest) {
        this.existsByEmailAddress(updateEmployeeRequest.getEmailAddress());
        this.existsByPhoneNumber(updateEmployeeRequest.getPhoneNumber());
        return updateEmployeeRequest;
    }

    public CreateEmployeeRequest checkCreateEmployeeRequest(CreateEmployeeRequest createEmployeeRequest) {
        this.checkSalary(createEmployeeRequest.getSalary());
        this.existsByEmailAddress(createEmployeeRequest.getEmailAddress());
        this.existsByPhoneNumber(createEmployeeRequest.getPhoneNumber());
        return createEmployeeRequest;
    }


    //----------------------------METHODS--------------------------------

    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(EMPLOYEE_LIST_NOT_FOUND, "Aradığınız kriterlere uygun çalışan bulunamadı");
        }
        return list;
    }

    public void checkSalary(double salary) {
        if (salary <= 0) {
            throw new ValidationException(VALIDATION_EXCEPTION, "Salary must be greater than zero");
        }
    }

    @Override
    public String fixName(String name) {
        return name.trim().toLowerCase();
    }


    @Override
    public void existsByPhoneNumber(String phoneNumber) {
        if (employeeRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistsException(PHONE_NUMBER_ALREADY_EXISTS, "This phone number already exist");
        }
    }

    @Override
    public void existsByEmailAddress(String email) {
        if (employeeRepository.existsByEmailAddress(email)) {
            throw new AlreadyExistsException(EMAIL_ADDRESS_ALREADY_EXISTS, "This email address already exist");
        }
    }

    @Override
    public void existsByEmailAddressAndIdNot(String emailAddress, int id) {
        //Kendisi hariç başka bir email ile aynı olup olmadığını kontrol etmek için
        if (employeeRepository.existsByEmailAddressAndIdNot(emailAddress, id)) {
            throw new AlreadyExistsException(EMAIL_ADDRESS_ALREADY_EXISTS, "This email address already exist !");
        }
    }


    @Override
    public void existsByPhoneNumberAndIdNot(String phoneNumber, int id) {
        if (employeeRepository.existsByPhoneNumberAndIdNot(phoneNumber, id)) {
            throw new AlreadyExistsException(PHONE_NUMBER_ALREADY_EXISTS, "This phone number address already exist !");
        }
    }
}
