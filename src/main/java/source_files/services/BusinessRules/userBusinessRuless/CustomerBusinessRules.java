package source_files.services.BusinessRules.userBusinessRuless;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.requests.userRequests.CreateCustomerRequest;
import source_files.data.requests.userRequests.UpdateCustomerRequest;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.exception.AlreadyExistsException;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.abstractsBusinessRules.BaseUserBusinessRulesService;
import source_files.services.entityServices.userEntityManagers.CustomerEntityServiceImpl;

import java.util.List;

import static source_files.exception.exceptionTypes.AlreadyExistsExceptionType.*;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.CUSTOMER_LIST_NOT_FOUND;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.RENTAL_DATA_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CustomerBusinessRules implements BaseUserBusinessRulesService {
    private final CustomerRepository customerRepository;
    private final CustomerEntityServiceImpl customerEntityServiceImpl;


    //--------------------- AUTO FIX METHODS ---------------------

    public CreateCustomerRequest fix(CreateCustomerRequest createCustomerRequest) {
        createCustomerRequest.setPhoneNumber(this.fixName(createCustomerRequest.getPhoneNumber()));
        createCustomerRequest.setName(this.fixName(createCustomerRequest.getName()));
        createCustomerRequest.setSurname(this.fixName(createCustomerRequest.getSurname()));
        createCustomerRequest.setEmailAddress(this.fixName(createCustomerRequest.getEmailAddress()));
        createCustomerRequest.setDrivingLicenseNumber(this.fixName(createCustomerRequest.getDrivingLicenseNumber()));
        return createCustomerRequest;
    }

    public UpdateCustomerRequest fix(UpdateCustomerRequest updateCustomerRequest) {
        updateCustomerRequest.setPhoneNumber(this.fixName(updateCustomerRequest.getPhoneNumber()));
        updateCustomerRequest.setName(this.fixName(updateCustomerRequest.getName()));
        updateCustomerRequest.setSurname(this.fixName(updateCustomerRequest.getSurname()));
        updateCustomerRequest.setEmailAddress(this.fixName(updateCustomerRequest.getEmailAddress()));
        return updateCustomerRequest;
    }

    //--------------------- AUTO CHECK METHODS ---------------------

    public void check(CreateCustomerRequest createCustomerRequest) {
        this.existsByPhoneNumber(createCustomerRequest.getPhoneNumber());
        this.existsByDrivingLicenseNumber(createCustomerRequest.getDrivingLicenseNumber());
        this.existsByEmailAddress(createCustomerRequest.getEmailAddress());
    }

    public void check(UpdateCustomerRequest updateCustomerRequest) {
        this.existsByPhoneNumberAndIdNot(updateCustomerRequest.getPhoneNumber(), updateCustomerRequest.getId());
        this.existsByDrivingLicenseNumberAndIdNot(updateCustomerRequest.getDrivingLicenseNumber(), updateCustomerRequest.getId());
        this.existsByEmailAddressAndIdNot(updateCustomerRequest.getEmailAddress(), updateCustomerRequest.getId());
    }


    //----------------------------METHODS--------------------------------

    @Override
    public void checkDataList(List<?> list) {
        if (list.isEmpty()) {
            throw new DataNotFoundException(CUSTOMER_LIST_NOT_FOUND);
        }

    }

    public void checkRentalHistory(List<RentalEntity> rentalHistory) {
        if (rentalHistory.isEmpty()) {
            throw new DataNotFoundException(RENTAL_DATA_NOT_FOUND);//list not found dönmedik çünkü message ı uygun değil.
        }
    }


    @Override
    public String fixName(String name) {
        return name.trim().toLowerCase();
    }

    @Override
    public void existsByPhoneNumber(String phoneNumber) {
        if (customerRepository.existsByPhoneNumber(phoneNumber)) {
            throw new AlreadyExistsException(PHONE_NUMBER_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByPhoneNumberAndIdNot(String phoneNumber, int id) {
        if (customerRepository.existsByPhoneNumberAndIdNot(phoneNumber, id)) {
            throw new AlreadyExistsException(PHONE_NUMBER_ALREADY_EXISTS);
        }
    }

    private void existsByDrivingLicenseNumber(String drivingLicenseNumber) {
        if (customerRepository.existsByDrivingLicenseNumberIgnoreCase(drivingLicenseNumber)) {
            throw new AlreadyExistsException(DRIVING_LICENSE_NUMBER_ALREADY_EXISTS);
        }
    }

    private void existsByDrivingLicenseNumberAndIdNot(String drivingLicenseNumber, int id) {
        if (customerRepository.existsByDrivingLicenseNumberIgnoreCaseAndIdNot(drivingLicenseNumber, id)) {
            throw new AlreadyExistsException(DRIVING_LICENSE_NUMBER_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByEmailAddress(String email) {
        if (customerRepository.existsByEmailAddressIgnoreCase(email)) {
            throw new AlreadyExistsException(EMAIL_ADDRESS_ALREADY_EXISTS);
        }
    }

    @Override
    public void existsByEmailAddressAndIdNot(String emailAddress, int id) {
        //Kendisi hariç başka bir email ile aynı olup olmadığını kontrol etmek için
        if (customerRepository.existsByEmailAddressIgnoreCaseAndIdNot(emailAddress, id)) {
            throw new AlreadyExistsException(EMAIL_ADDRESS_ALREADY_EXISTS);
        }
    }
}
