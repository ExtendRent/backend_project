package source_files.services.BusinessRules;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.requests.userRequests.AddCustomerRequest;
import source_files.data.requests.userRequests.UpdateCustomerRequest;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.CustomerEntityManager;

import java.util.List;

import static source_files.exception.AlreadyExistsExceptionType.*;
import static source_files.exception.NotFoundExceptionType.CUSTOMER_LIST_NOT_FOUND;

@AllArgsConstructor
@Service
public class CustomerBusinessRules implements BaseBusinessRulesService {
    private final CustomerRepository customerRepository;
    private final CustomerEntityManager customerEntityManager;

    @Override
    public List<?> checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(CUSTOMER_LIST_NOT_FOUND, "Aradığınız kriterlere uygun müşteri bulunamadı");
        }
        return list;
    }

    public AddCustomerRequest fixAddCustomerRequest(AddCustomerRequest addCustomerRequest) {
        addCustomerRequest.setPhoneNumber(this.fixName(addCustomerRequest.getPhoneNumber()));
        addCustomerRequest.setName(this.fixName(addCustomerRequest.getName()));
        addCustomerRequest.setSurname(this.fixName(addCustomerRequest.getSurname()));
        addCustomerRequest.setEmailAddress(this.fixName(addCustomerRequest.getEmailAddress()));
        addCustomerRequest.setDrivingLicenseNumber(this.fixName(addCustomerRequest.getDrivingLicenseNumber()));
        return addCustomerRequest;
    }

    public AddCustomerRequest checkAddCustomerRequest(AddCustomerRequest addCustomerRequest) {
        this.existsByPhoneNumber(addCustomerRequest.getPhoneNumber());
        this.existsByDrivingLicenseNumber(addCustomerRequest.getDrivingLicenseNumber());
        this.existsByEmailAddress(addCustomerRequest.getEmailAddress());
        return addCustomerRequest;
    }

    public UpdateCustomerRequest fixUpdateCustomerRequest(UpdateCustomerRequest updateCustomerRequest) {
        updateCustomerRequest.setPhoneNumber(this.fixName(updateCustomerRequest.getPhoneNumber()));
        updateCustomerRequest.setName(this.fixName(updateCustomerRequest.getName()));
        updateCustomerRequest.setSurname(this.fixName(updateCustomerRequest.getSurname()));
        updateCustomerRequest.setEmailAddress(this.fixName(updateCustomerRequest.getEmailAddress()));
        updateCustomerRequest.setDrivingLicenseNumber(this.fixName(updateCustomerRequest.getDrivingLicenseNumber()));
        return updateCustomerRequest;
    }

    public UpdateCustomerRequest checkUpdateCustomerRequest(UpdateCustomerRequest updateCustomerRequest) {
        this.existsByPhoneNumber(updateCustomerRequest.getPhoneNumber());
        this.existsByDrivingLicenseNumber(updateCustomerRequest.getDrivingLicenseNumber());
        this.existsByEmailAddress(updateCustomerRequest.getEmailAddress());
        updateCustomerRequest.setId(this.customerEntityManager.getById(updateCustomerRequest.getId()).getId());
        return updateCustomerRequest;
    }

    @Override
    public String fixName(String name) {
        return name.trim().toLowerCase();
    }

    //---------------AUTO CHECKING METHODS--------------------------------
    private void existsByPhoneNumber(String phoneNumber) {
        if (customerRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistsException(PHONE_NUMBER_ALREADY_EXISTS, "This phone number already exist");
        }
    }

    private void existsByDrivingLicenseNumber(String drivingLicenseNumber) {
        if (customerRepository.existsByDrivingLicenseNumber(drivingLicenseNumber)) {
            throw new AlreadyExistsException(DRIVING_LICENSE_NUMBER_ALREADY_EXISTS, "This driving license number already exist");

        }
    }

    public void existsByEmailAddress(String email) {
        if (customerRepository.existsByEmailAddress(email)) {
            throw new AlreadyExistsException(EMAIL_ADDRESS_ALREADY_EXISTS, "This email address already exist");
        }
    }
}
