package src.service.user.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.user.admin.requests.CreateAdminRequest;
import src.controller.user.admin.requests.UpdateAdminRequest;
import src.core.exception.AlreadyExistsException;
import src.core.exception.DataNotFoundException;
import src.core.exception.ValidationException;
import src.repository.user.admin.AdminRepository;
import src.service.businessrules.abstracts.BaseUserRules;

import java.util.List;

import static src.core.exception.type.AlreadyExistsExceptionType.EMAIL_ADDRESS_ALREADY_EXISTS;
import static src.core.exception.type.AlreadyExistsExceptionType.PHONE_NUMBER_ALREADY_EXISTS;
import static src.core.exception.type.NotFoundExceptionType.ADMIN_LIST_NOT_FOUND;
import static src.core.exception.type.ValidationExceptionType.VALIDATION_EXCEPTION;

@RequiredArgsConstructor
@Service
public class AdminRules implements BaseUserRules {
    private final AdminRepository adminRepository;

    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(ADMIN_LIST_NOT_FOUND);
        }
    }

    //--------------------- AUTO FIX METHODS ---------------------
    public CreateAdminRequest fix(CreateAdminRequest createAdminRequest) {
        createAdminRequest.setPhoneNumber(this.fixName(createAdminRequest.getPhoneNumber()));
        createAdminRequest.setName(this.fixName(createAdminRequest.getName()));
        createAdminRequest.setSurname(this.fixName(createAdminRequest.getSurname()));
        createAdminRequest.setEmailAddress(this.fixName(createAdminRequest.getEmailAddress()));
        return createAdminRequest;
    }

    public UpdateAdminRequest fix(UpdateAdminRequest updateAdminRequest) {
        updateAdminRequest.setPhoneNumber(this.fixName(updateAdminRequest.getPhoneNumber()));
        updateAdminRequest.setName(this.fixName(updateAdminRequest.getName()));
        updateAdminRequest.setSurname(this.fixName(updateAdminRequest.getSurname()));
        updateAdminRequest.setEmailAddress(this.fixName(updateAdminRequest.getEmailAddress()));
        return updateAdminRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public void check(CreateAdminRequest createAdminRequest) {
        this.checkSalary(createAdminRequest.getSalary());
        this.existsByEmailAddress(createAdminRequest.getEmailAddress());
        this.existsByPhoneNumber(createAdminRequest.getPhoneNumber());
    }

    public void check(UpdateAdminRequest updateAdminRequest) {
        this.existsByEmailAddressAndIdNot(updateAdminRequest.getEmailAddress(), updateAdminRequest.getId());
        this.existsByPhoneNumberAndIdNot(updateAdminRequest.getPhoneNumber(), updateAdminRequest.getId());
    }


    //----------------------------METHODS--------------------------------

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
        if (adminRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistsException(PHONE_NUMBER_ALREADY_EXISTS);
        }
    }


    @Override
    public void existsByPhoneNumberAndIdNot(String phoneNumber, int id) {
        if (adminRepository.existsByPhoneNumberIgnoreCaseAndIdNot(phoneNumber, id)) {
            throw new AlreadyExistsException(PHONE_NUMBER_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByEmailAddressAndIdNot(String emailAddress, int id) {
        //Kendisi hariç başka bir email ile aynı olup olmadığını kontrol etmek için
        if (adminRepository.existsByEmailAddressIgnoreCaseAndIdNot(emailAddress, id)) {
            throw new AlreadyExistsException(EMAIL_ADDRESS_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByEmailAddress(String emailAddress) {
        if (adminRepository.existsByEmailAddressIgnoreCase(emailAddress)) {
            throw new AlreadyExistsException(EMAIL_ADDRESS_ALREADY_EXISTS);
        }
    }

}
