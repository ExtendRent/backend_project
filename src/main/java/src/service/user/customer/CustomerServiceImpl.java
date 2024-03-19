package src.service.user.customer;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.controller.rental.response.RentalResponse;
import src.controller.user.customer.request.CreateCustomerRequest;
import src.controller.user.customer.request.UpdateCustomerRequest;
import src.controller.user.customer.response.CustomerResponse;
import src.repository.image.UserImageEntity;
import src.repository.rental.RentalEntity;
import src.repository.user.customer.CustomerEntity;
import src.repository.user.customer.CustomerEntityService;
import src.repository.user.customer.CustomerRepository;
import src.service.image.user.UserImageService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerRules rules;
    private final CustomerEntityService entityService;
    private final PasswordEncoder passwordEncoder;
    private final UserImageService userImageService;

    @Override
    public void create(CreateCustomerRequest createCustomerRequest) {
        try {
            createCustomerRequest = rules.fix(createCustomerRequest);
            rules.check(createCustomerRequest);
            createCustomerRequest.setPassword(passwordEncoder.encode(createCustomerRequest.getPassword()));
            entityService.create(createCustomerRequest);
        } catch (Exception e) {
            userImageService.delete(createCustomerRequest.getUserImageEntityId());
            throw e;
        }
    }

    @Override
    public CustomerResponse update(UpdateCustomerRequest updateCustomerRequest) {
        updateCustomerRequest = rules.fix(updateCustomerRequest);
        rules.check(updateCustomerRequest);
        updateCustomerRequest.setPassword(passwordEncoder.encode(updateCustomerRequest.getPassword()));
        UserImageEntity userImage = userImageService.getById(updateCustomerRequest.getUserImageEntityId());
        if (userImage.getId() != updateCustomerRequest.getUserImageEntityId()) {
            userImageService.delete(userImage.getId());
        }
        return entityService.update(updateCustomerRequest).toModel();
    }


    @Override
    public CustomerResponse getById(int id) {
        return entityService.getById(id).toModel();

    }

    @Override
    public CustomerResponse getByEmailAddress(String emailAddress) {
        return entityService.getByEmailAddress(emailAddress).toModel();
    }

    @Override
    public List<CustomerResponse> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Transactional
    @Override
    public List<CustomerResponse> getAllByDeletedState(boolean isDeleted) {
        return mapToDTOList(entityService.getAllByDeletedState(isDeleted));
    }

    @Override
    public void addRental(int customerId, RentalEntity rentalEntity) {
        CustomerEntity customerEntity = entityService.getById(customerId);
        customerEntity.getRentalHistory().add(rentalEntity);
        entityService.update(customerEntity);
    }

    @Override
    public void removeRental(int customerId, RentalEntity rentalEntity) {
        CustomerEntity customerEntity = entityService.getById(customerId);
        customerEntity.getRentalHistory().remove(rentalEntity);
        entityService.update(customerEntity);
    }


    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            CustomerEntity customerEntity = entityService.getById(id);
            entityService.delete(customerEntity);
            userImageService.delete(customerEntity.getUserImageEntity().getId());
        } else {
            softDelete(id);
        }
    }

    @Override
    public List<RentalResponse> getRentalHistory(int customerId) {
        CustomerEntity customerEntity = entityService.getById(customerId);
        List<RentalEntity> rentalHistory = customerEntity.getRentalHistory();
        return rentalHistory.stream().map(RentalEntity::toModel).toList();
    }

    @Override
    public void softDelete(int id) {
        CustomerEntity customerEntity = entityService.getById(id);
        customerEntity.setIsDeleted(true);
        customerEntity.setDeletedAt(LocalDateTime.now());
        customerRepository.save(customerEntity);
    }

    @Override
    public int getCountByDeletedState(boolean isDeleted) {
        return entityService.getCountByDeletedState(isDeleted);
    }

    @Override
    public int getCountByStatus(String status) {
        return entityService.getCountByStatus(status.trim().toUpperCase());
    }

    private List<CustomerResponse> mapToDTOList(List<CustomerEntity> customerEntities) {
        rules.checkDataList(customerEntities);
        return customerEntities.stream().map(CustomerEntity::toModel).toList();
    }

}
