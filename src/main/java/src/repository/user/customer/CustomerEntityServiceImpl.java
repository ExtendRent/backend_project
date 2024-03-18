package src.repository.user.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controller.user.customer.requests.CreateCustomerRequest;
import src.controller.user.customer.requests.UpdateCustomerRequest;
import src.core.exception.DataNotFoundException;
import src.repository.license.DrivingLicenseTypeEntityService;
import src.service.image.user.UserImageService;
import src.service.user.model.DefaultUserStatus;

import java.util.List;

import static src.core.exception.type.NotFoundExceptionType.CUSTOMER_DATA_NOT_FOUND;
import static src.service.user.model.DefaultUserStatus.PENDING_VERIFYING;

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
