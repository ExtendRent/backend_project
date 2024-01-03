package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.userRequests.AddAdminRequest;
import source_files.data.requests.userRequests.UpdateAdminRequest;
import source_files.dataAccess.userRepositories.AdminRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.AdminEntityManager;

import java.util.List;

import static source_files.exception.AlreadyExistsExceptionType.EMAIL_ADDRESS_ALREADY_EXISTS;
import static source_files.exception.AlreadyExistsExceptionType.PHONE_NUMBER_ALREADY_EXISTS;
import static source_files.exception.NotFoundExceptionType.ADMIN_LIST_NOT_FOUND;

@AllArgsConstructor
@Service
public class AdminBusinessRules implements BaseUserBusinessRulesService {
    private final AdminRepository adminRepository;
    private final AdminEntityManager adminEntityManager;

    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(ADMIN_LIST_NOT_FOUND, "Aradığınız kriterlere uygun admin bulunamadı");
        }
        return list;
    }

    //--------------------- AUTO FIX METHODS ---------------------
    public AddAdminRequest fixAddAdminRequest(AddAdminRequest addAdminRequest) {
        addAdminRequest.setPhoneNumber(this.fixName(addAdminRequest.getPhoneNumber()));
        addAdminRequest.setName(this.fixName(addAdminRequest.getName()));
        addAdminRequest.setSurname(this.fixName(addAdminRequest.getSurname()));
        addAdminRequest.setEmailAddress(this.fixName(addAdminRequest.getEmailAddress()));
        return addAdminRequest;
    }

    public UpdateAdminRequest fixUpdateAdminRequest(UpdateAdminRequest updateAdminRequest) {
        updateAdminRequest.setPhoneNumber(this.fixName(updateAdminRequest.getPhoneNumber()));
        updateAdminRequest.setName(this.fixName(updateAdminRequest.getName()));
        updateAdminRequest.setSurname(this.fixName(updateAdminRequest.getSurname()));
        updateAdminRequest.setEmailAddress(this.fixName(updateAdminRequest.getEmailAddress()));
        return updateAdminRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------
    public AddAdminRequest checkAddAdminRequest(AddAdminRequest addAdminRequest) {
        this.existsByEmailAddress(addAdminRequest.getEmailAddress());
        this.existsByPhoneNumber(addAdminRequest.getPhoneNumber());
        return addAdminRequest;
    }

    public UpdateAdminRequest checkUpdateAdminRequest(UpdateAdminRequest updateAdminRequest) {

        this.existsByEmailAddressAndIdNot(updateAdminRequest.getEmailAddress(), updateAdminRequest.getId());
        this.existsByPhoneNumberAndIdNot(updateAdminRequest.getPhoneNumber(), updateAdminRequest.getId());

        return updateAdminRequest;
    }


    //----------------------------METHODS--------------------------------

    @Override
    public String fixName(String name) {
        return name.trim().toLowerCase();
    }

    @Override
    public void existsByPhoneNumber(String phoneNumber) {
        if (adminRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistsException(PHONE_NUMBER_ALREADY_EXISTS, "This phone number already exist");
        }
    }


    @Override
    public void existsByPhoneNumberAndIdNot(String phoneNumber, int id) {
        if (adminRepository.existsByPhoneNumberAndIdNot(phoneNumber, id)) {
            throw new AlreadyExistsException(PHONE_NUMBER_ALREADY_EXISTS, "This email address already exist !");
        }
    }

    @Override
    public void existsByEmailAddressAndIdNot(String emailAddress, int id) {
        //Kendisi hariç başka bir email ile aynı olup olmadığını kontrol etmek için
        if (adminRepository.existsByEmailAddressAndIdNot(emailAddress, id)) {
            throw new AlreadyExistsException(EMAIL_ADDRESS_ALREADY_EXISTS, "This email address already exist !");
        }
    }

    @Override
    public void existsByEmailAddress(String emailAddress) {
        if (adminRepository.existsByEmailAddress(emailAddress)) {
            throw new AlreadyExistsException(EMAIL_ADDRESS_ALREADY_EXISTS, "This email address already exist :)");
        }
    }

}
