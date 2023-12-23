package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.userRequests.AddAdminRequest;
import source_files.data.requests.userRequests.AddCustomerRequest;
import source_files.data.requests.userRequests.UpdateAdminRequest;
import source_files.dataAccess.userRepositories.AdminRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.AdminEntityManager;

import java.util.List;

import static source_files.exception.AlreadyExistsExceptionType.*;
import static source_files.exception.NotFoundExceptionType.*;
@AllArgsConstructor
@Service
public class AdminBusinessRules implements BaseBusinessRulesService {
    private final AdminRepository adminRepository;
    private final AdminEntityManager adminEntityManager;

    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()){
            throw new DataNotFoundException(ADMIN_LIST_NOT_FOUND,"Aradığınız kriterlere uygun admin bulunamadı");
        }
        return list;
    }
    public AddAdminRequest fixAddAdminRequest(AddAdminRequest addAdminRequest){
        addAdminRequest.setPhoneNumber(this.fixName(addAdminRequest.getPhoneNumber()));
        addAdminRequest.setName(this.fixName(addAdminRequest.getName()));
        addAdminRequest.setSurname(this.fixName(addAdminRequest.getSurname()));
        addAdminRequest.setEmailAddress(this.fixName(addAdminRequest.getEmailAddress()));
        return addAdminRequest;
    }
    public UpdateAdminRequest fixUpdateAdminRequest(UpdateAdminRequest updateAdminRequest){
        updateAdminRequest.setPhoneNumber(this.fixName(updateAdminRequest.getPhoneNumber()));
        updateAdminRequest.setName(this.fixName(updateAdminRequest.getName()));
        updateAdminRequest.setSurname(this.fixName(updateAdminRequest.getSurname()));
        updateAdminRequest.setEmailAddress(this.fixName(updateAdminRequest.getEmailAddress()));
        return updateAdminRequest;
    }

    public AddAdminRequest checkAddAdminRequest (AddAdminRequest addAdminRequest) {
        this.existsByEmailAddress(addAdminRequest.getEmailAddress());
        this.existsByPhoneNumber(addAdminRequest.getPhoneNumber());
        return addAdminRequest;
    }
    public UpdateAdminRequest checkUpdateAdminRequest (UpdateAdminRequest updateAdminRequest) {
        this.existsByEmailAddress(updateAdminRequest.getEmailAddress());
        this.existsByPhoneNumber(updateAdminRequest.getPhoneNumber());
        updateAdminRequest.setId(this.adminEntityManager.getById(updateAdminRequest.getId()).getId());
        return updateAdminRequest ;
    }


    @Override
    public String fixName(String name) {
        return name.trim().toLowerCase();
    }

    //---------------AUTO CHECKING METHODS--------------------------------
    private void existsByPhoneNumber(String phoneNumber) {
        if (adminRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistsException(PHONE_NUMBER_ALREADY_EXISTS, "This phone number already exist");
        }
    }
    public void existsByEmailAddress(String email){
        if (adminRepository.existsByEmailAddress(email)){
            throw new AlreadyExistsException(EMAIL_ADDRESS_ALREADY_EXISTS,"This email address already exist");
        }
    }


}
