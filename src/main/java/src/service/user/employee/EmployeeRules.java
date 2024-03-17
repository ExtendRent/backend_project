package src.service.user.employee;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.user.employee.requests.CreateEmployeeRequest;
import src.controller.user.employee.requests.UpdateEmployeeRequest;
import src.core.exception.AlreadyExistsException;
import src.core.exception.DataNotFoundException;
import src.core.exception.ValidationException;
import src.repository.user.employee.EmployeeRepository;
import src.service.businessrules.abstracts.BaseUserRules;

import java.util.List;

import static src.core.exception.type.AlreadyExistsExceptionType.EMAIL_ADDRESS_ALREADY_EXISTS;
import static src.core.exception.type.AlreadyExistsExceptionType.PHONE_NUMBER_ALREADY_EXISTS;
import static src.core.exception.type.NotFoundExceptionType.EMPLOYEE_LIST_NOT_FOUND;
import static src.core.exception.type.ValidationExceptionType.VALIDATION_EXCEPTION;

@RequiredArgsConstructor
@Service
public class EmployeeRules implements BaseUserRules {
    private final EmployeeRepository employeeRepository;

    //--------------------- AUTO FIX METHODS ---------------------

    public CreateEmployeeRequest fix(CreateEmployeeRequest createEmployeeRequest) {
        createEmployeeRequest.setPhoneNumber(fixName(createEmployeeRequest.getPhoneNumber()));
        createEmployeeRequest.setName(fixName(createEmployeeRequest.getName()));
        createEmployeeRequest.setSurname(fixName(createEmployeeRequest.getSurname()));
        createEmployeeRequest.setEmailAddress(fixName(createEmployeeRequest.getEmailAddress()));
        return createEmployeeRequest;
    }

    public UpdateEmployeeRequest fix(UpdateEmployeeRequest updateEmployeeRequest) {
        updateEmployeeRequest.setPhoneNumber(fixName(updateEmployeeRequest.getPhoneNumber()));
        updateEmployeeRequest.setName(fixName(updateEmployeeRequest.getName()));
        updateEmployeeRequest.setSurname(fixName(updateEmployeeRequest.getSurname()));
        updateEmployeeRequest.setEmailAddress(fixName(updateEmployeeRequest.getEmailAddress()));
        return updateEmployeeRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public void check(CreateEmployeeRequest createEmployeeRequest) {
        checkSalary(createEmployeeRequest.getSalary());
        existsByEmailAddress(createEmployeeRequest.getEmailAddress());
        existsByPhoneNumber(createEmployeeRequest.getPhoneNumber());
    }

    public void check(UpdateEmployeeRequest updateEmployeeRequest) {
        existsByEmailAddressAndIdNot(updateEmployeeRequest.getEmailAddress(), updateEmployeeRequest.getId());
        existsByPhoneNumberAndIdNot(updateEmployeeRequest.getPhoneNumber(), updateEmployeeRequest.getId());
    }


    //----------------------------METHODS--------------------------------

    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(EMPLOYEE_LIST_NOT_FOUND);
        }

    }

    public void checkSalary(double salary) {
        if (salary <= 0) {
            throw new ValidationException(VALIDATION_EXCEPTION, "Maaş en 0 dan büyük olmalıdır.");
        }
    }

    @Override
    public String fixName(String name) {
        return name.trim().toLowerCase();
    }

    @Override
    public void existsByEmailAddress(String email) {
        if (employeeRepository.existsByEmailAddressIgnoreCase(email)) {
            throw new AlreadyExistsException(EMAIL_ADDRESS_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByEmailAddressAndIdNot(String emailAddress, int id) {
        //Kendisi hariç başka bir email ile aynı olup olmadığını kontrol etmek için
        if (employeeRepository.existsByEmailAddressIgnoreCaseAndIdNot(emailAddress, id)) {
            throw new AlreadyExistsException(EMAIL_ADDRESS_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByPhoneNumber(String phoneNumber) {
        if (employeeRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistsException(PHONE_NUMBER_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByPhoneNumberAndIdNot(String phoneNumber, int id) {
        if (employeeRepository.existsByPhoneNumberAndIdNot(phoneNumber, id)) {
            throw new AlreadyExistsException(PHONE_NUMBER_ALREADY_EXISTS);
        }
    }
}
