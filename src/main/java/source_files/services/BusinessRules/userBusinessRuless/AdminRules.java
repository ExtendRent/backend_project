package source_files.services.BusinessRules.userBusinessRuless;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.userRequests.CreateAdminRequest;
import source_files.data.requests.userRequests.UpdateAdminRequest;
import source_files.dataAccess.userRepositories.AdminRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.exception.ValidationException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseUserRules;

import java.util.List;

import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.EMAIL_ADDRESS_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.PHONE_NUMBER_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.ADMIN_LIST_NOT_FOUND;
import static source_files.exception.exceptionTypes.ValidationExceptionType.VALIDATION_EXCEPTION;

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
