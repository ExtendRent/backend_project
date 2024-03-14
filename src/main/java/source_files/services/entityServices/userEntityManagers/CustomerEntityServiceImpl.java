package source_files.services.entityServices.userEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.controllers.user.requests.CreateCustomerRequest;
import source_files.controllers.user.requests.UpdateCustomerRequest;
import source_files.data.enums.defaultDataEnums.Status.DefaultUserStatus;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.exception.DataNotFoundException;
import source_files.repositories.user.CustomerRepository;
import source_files.services.Image.UserImageService;
import source_files.services.entityServices.abstracts.DrivingLicenseTypeEntityService;
import source_files.services.entityServices.abstracts.userAbstract.CustomerEntityService;

import java.util.List;

import static source_files.data.enums.defaultDataEnums.Status.DefaultUserStatus.PENDING_VERIFYING;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.CUSTOMER_DATA_NOT_FOUND;

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
