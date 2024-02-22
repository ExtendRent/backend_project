package source_files.services.BusinessRules.userBusinessRuless;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.userEntities.AdminEntity;
import source_files.data.requests.userRequests.CreateAdminRequest;
import source_files.data.requests.userRequests.UpdateAdminRequest;
import source_files.dataAccess.userRepositories.AdminRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.exception.ValidationException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseUserBusinessRulesService;

import java.util.List;

import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.EMAIL_ADDRESS_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.PHONE_NUMBER_ALREADY_EXISTS;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.ADMIN_LIST_NOT_FOUND;
import static source_files.exception.exceptionTypes.ValidationExceptionType.VALIDATION_EXCEPTION;

@RequiredArgsConstructor
@Service
public class AdminBusinessRules implements BaseUserBusinessRulesService {
    private final AdminRepository adminRepository;

    @Override
    public List<AdminEntity> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(ADMIN_LIST_NOT_FOUND);
        }

        return list.stream().map(adminEntity -> (AdminEntity) adminEntity).toList();
    }

    //--------------------- AUTO FIX METHODS ---------------------
    public CreateAdminRequest fixCreateAdminRequest(CreateAdminRequest createAdminRequest) {
        createAdminRequest.setPhoneNumber(this.fixName(createAdminRequest.getPhoneNumber()));
        createAdminRequest.setName(this.fixName(createAdminRequest.getName()));
        createAdminRequest.setSurname(this.fixName(createAdminRequest.getSurname()));
        createAdminRequest.setEmailAddress(this.fixName(createAdminRequest.getEmailAddress()));
        return createAdminRequest;
    }

    public UpdateAdminRequest fixUpdateAdminRequest(UpdateAdminRequest updateAdminRequest) {
        updateAdminRequest.setPhoneNumber(this.fixName(updateAdminRequest.getPhoneNumber()));
        updateAdminRequest.setName(this.fixName(updateAdminRequest.getName()));
        updateAdminRequest.setSurname(this.fixName(updateAdminRequest.getSurname()));
        updateAdminRequest.setEmailAddress(this.fixName(updateAdminRequest.getEmailAddress()));
        return updateAdminRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public CreateAdminRequest checkCreateAdminRequest(CreateAdminRequest createAdminRequest) {
        this.checkSalary(createAdminRequest.getSalary());
        this.existsByEmailAddress(createAdminRequest.getEmailAddress());
        this.existsByPhoneNumber(createAdminRequest.getPhoneNumber());
        return createAdminRequest;
    }

    public UpdateAdminRequest checkUpdateAdminRequest(UpdateAdminRequest updateAdminRequest) {

        this.existsByEmailAddressAndIdNot(updateAdminRequest.getEmailAddress(), updateAdminRequest.getId());
        this.existsByPhoneNumberAndIdNot(updateAdminRequest.getPhoneNumber(), updateAdminRequest.getId());

        return updateAdminRequest;
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
        if (adminRepository.existsByPhoneNumberAndIdNot(phoneNumber, id)) {
            throw new AlreadyExistsException(PHONE_NUMBER_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByEmailAddressAndIdNot(String emailAddress, int id) {
        //Kendisi hariç başka bir email ile aynı olup olmadığını kontrol etmek için
        if (adminRepository.existsByEmailAddressAndIdNot(emailAddress, id)) {
            throw new AlreadyExistsException(EMAIL_ADDRESS_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByEmailAddress(String emailAddress) {
        if (adminRepository.existsByEmailAddress(emailAddress)) {
            throw new AlreadyExistsException(EMAIL_ADDRESS_ALREADY_EXISTS);
        }
    }

}
