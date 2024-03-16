package src.services.user.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.user.requests.customer.CreateCustomerRequest;
import src.controllers.user.requests.customer.UpdateCustomerRequest;
import src.core.exception.DataNotFoundException;
import src.data.enums.default_data_enums.status.DefaultUserStatus;
import src.data.models.user_entities.CustomerEntity;
import src.repositories.user.CustomerRepository;
import src.services.image.user.UserImageService;
import src.services.item.driving_license.DrivingLicenseTypeEntityService;

import java.util.List;

import static src.core.exception.exception_types.NotFoundExceptionType.CUSTOMER_DATA_NOT_FOUND;
import static src.data.enums.default_data_enums.status.DefaultUserStatus.PENDING_VERIFYING;

@RequiredArgsConstructor
@Service
public class CustomerEntityServiceImpl implements CustomerEntityService {

    private final CustomerRepository repository;
    private final DrivingLicenseTypeEntityService drivingLicenseTypeEntityService;
    private final UserImageService userImageService;

    @Override
    public CustomerEntity create(CreateCustomerRequest createCustomerRequest) {
        CustomerEntity customerEntity = CustomerEntity.customerBuilder()
                .name(createCustomerRequest.getName())
                .surname(createCustomerRequest.getSurname())
                .emailAddress(createCustomerRequest.getEmailAddress())
                .password(createCustomerRequest.getPassword())
                .phoneNumber(createCustomerRequest.getPhoneNumber())
                .drivingLicenseNumber(createCustomerRequest.getDrivingLicenseNumber())
                .drivingLicenseTypeEntity(drivingLicenseTypeEntityService.getById(
                        createCustomerRequest.getDrivingLicenseTypeEntityId()))
                .userImageEntity(userImageService.getById(createCustomerRequest.getUserImageEntityId()))
                .status(PENDING_VERIFYING)
                .build();
        return repository.save(customerEntity);
    }

    @Override
    public CustomerEntity update(UpdateCustomerRequest updateCustomerRequest) {
        CustomerEntity customerEntity = CustomerEntity.customerBuilder()
                .id(updateCustomerRequest.getId())
                .name(updateCustomerRequest.getName())
                .surname(updateCustomerRequest.getSurname())
                .emailAddress(updateCustomerRequest.getEmailAddress())
                .password(updateCustomerRequest.getPassword())
                .phoneNumber(updateCustomerRequest.getPhoneNumber())
                .drivingLicenseNumber(updateCustomerRequest.getDrivingLicenseNumber())
                .drivingLicenseTypeEntity(drivingLicenseTypeEntityService.getById(
                        updateCustomerRequest.getDrivingLicenseTypeEntityId()))
                .status(updateCustomerRequest.getStatus())
                .userImageEntity(userImageService.getById(updateCustomerRequest.getUserImageEntityId()))
                .status(PENDING_VERIFYING)
                .build();
        return repository.save(customerEntity);
    }

    @Override
    public CustomerEntity update(CustomerEntity customerEntity) {
        return repository.save(customerEntity);
    }

    @Override
    public CustomerEntity getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(CUSTOMER_DATA_NOT_FOUND));
    }

    @Override
    public CustomerEntity getByEmailAddress(String emailAddress) {
        return repository.findByEmailAddress(emailAddress).orElseThrow(() -> new DataNotFoundException(
                CUSTOMER_DATA_NOT_FOUND));
    }

    @Override
    public List<CustomerEntity> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public int getCountByDeletedState(boolean isDeleted) {
        return repository.countByIsDeleted(isDeleted);
    }

    @Override
    public int getCountByStatus(String status) {
        return repository.countByStatus(DefaultUserStatus.valueOf(status.toUpperCase().trim()));
    }

    @Override
    public void delete(CustomerEntity customerEntity) {
        repository.delete(customerEntity);
    }


    @Override
    public List<CustomerEntity> getAll() {
        return repository.findAll();
    }


}
