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
public class EmployeeBusinessRules implements BaseUserBusinessRulesService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeEntityManager employeeEntityManager;


    //--------------------- AUTO FIX METHODS ---------------------

    public AddEmployeeRequest fixAddEmployeeRequest(AddEmployeeRequest addEmployeeRequest) {
        addEmployeeRequest.setPhoneNumber(this.fixName(addEmployeeRequest.getPhoneNumber()));
        addEmployeeRequest.setName(this.fixName(addEmployeeRequest.getName()));
        addEmployeeRequest.setSurname(this.fixName(addEmployeeRequest.getSurname()));
        addEmployeeRequest.setEmailAddress(this.fixName(addEmployeeRequest.getEmailAddress()));
        return addEmployeeRequest;
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

    public AddEmployeeRequest checkAddEmployeeRequest(AddEmployeeRequest addEmployeeRequest) {
        this.existsByEmailAddress(addEmployeeRequest.getEmailAddress());
        this.existsByPhoneNumber(addEmployeeRequest.getPhoneNumber());
        return addEmployeeRequest;
    }


    //----------------------------METHODS--------------------------------

    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(EMPLOYEE_LIST_NOT_FOUND, "Aradığınız kriterlere uygun çalışan bulunamadı");
        }
        return list;
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
