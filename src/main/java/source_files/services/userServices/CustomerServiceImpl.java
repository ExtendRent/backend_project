package source_files.services.userServices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import source_files.data.DTO.paperWorkDTOs.RentalDTO;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.models.imageEntities.UserImageEntity;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.data.requests.userRequests.CreateCustomerRequest;
import source_files.data.requests.userRequests.UpdateCustomerRequest;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.services.BusinessRules.userBusinessRuless.CustomerBusinessRules;
import source_files.services.entityServices.abstracts.userAbstract.CustomerEntityService;
import source_files.services.systemServices.ImageServices.UserImageService;
import source_files.services.userServices.abstracts.CustomerService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerBusinessRules rules;
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
    public CustomerDTO update(UpdateCustomerRequest updateCustomerRequest) {
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
    public CustomerDTO getById(int id) {
        return entityService.getById(id).toModel();

    }

    @Override
    public CustomerDTO getByEmailAddress(String emailAddress) {
        return entityService.getByEmailAddress(emailAddress).toModel();
    }

    @Override
    public List<CustomerDTO> getAll() {
        return mapToDTOList(entityService.getAll());
    }

    @Override
    public List<CustomerDTO> getAllByDeletedState(boolean isDeleted) {
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
    public List<RentalDTO> getRentalHistory(int customerId) {
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

    private List<CustomerDTO> mapToDTOList(List<CustomerEntity> customerEntities) {
        rules.checkDataList(customerEntities);
        return customerEntities.stream().map(CustomerEntity::toModel).toList();
    }

}
