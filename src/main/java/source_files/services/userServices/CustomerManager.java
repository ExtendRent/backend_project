package source_files.services.userServices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
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

import static source_files.data.enums.defaultDataEnums.Status.DefaultUserStatus.PENDING_VERIFYING;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {

    private final ModelMapperService mapper;
    private final CustomerRepository customerRepository;
    private final CustomerBusinessRules rules;
    private final CustomerEntityService entityService;
    private final PasswordEncoder passwordEncoder;
    private final UserImageService userImageService;

    @Override
    public void create(CreateCustomerRequest createCustomerRequest) {
        try {
            CustomerEntity customerEntity = mapper.forRequest()
                    .map(rules.checkCreateCustomerRequest(
                            rules.fixCreateCustomerRequest(createCustomerRequest)), CustomerEntity.class
                    );
            customerEntity.setUserImageEntity(userImageService.getById(createCustomerRequest.getUserImageEntityId()));
            customerEntity.setPassword(passwordEncoder.encode(customerEntity.getPassword()));
            customerEntity.setStatus(PENDING_VERIFYING);
            entityService.create(customerEntity);
        } catch (Exception e) {
            userImageService.delete(createCustomerRequest.getUserImageEntityId());
            throw e;
        }
    }

    @Override
    public CustomerDTO update(UpdateCustomerRequest updateCustomerRequest) {
        CustomerEntity customerEntity = entityService.getById(updateCustomerRequest.getId());
        UserImageEntity userImage = customerEntity.getUserImageEntity();

        if (userImage.getId() != updateCustomerRequest.getUserImageEntityId()) {
            userImageService.delete(userImage.getId());
        }
        customerEntity = mapper.forRequest()
                .map(rules.checkUpdateCustomerRequest(
                        rules.fixUpdateCustomerRequest(updateCustomerRequest)), CustomerEntity.class
                );

        return mapper.forResponse().map(entityService.create(customerEntity), CustomerDTO.class);
    }

    @Override
    public CustomerDTO getById(int id) {
        return maptoDto(entityService.getById(id));

    }

    @Override
    public CustomerDTO getByEmailAddress(String emailAddress) {
        return maptoDto(entityService.getByEmailAddress(emailAddress));
    }

    @Override
    public List<CustomerDTO> getAll() {
        List<CustomerDTO> customers = entityService.getAll().stream().map(this::maptoDto).toList();
        rules.checkDataList(customers);
        return customers;
    }

    @Override
    public List<CustomerDTO> getAllByDeletedState(boolean isDeleted) {
        List<CustomerDTO> customers = entityService.getAllByDeletedState(isDeleted).stream().map(this::maptoDto).toList();
        rules.checkDataList(customers);
        return customers;
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
        return customerEntity.getRentalHistory().stream().map(rentalEntity -> mapper.forResponse()
                .map(rentalEntity, RentalDTO.class)).toList();
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

    private CustomerDTO maptoDto(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = mapper.forResponse().map(customerEntity, CustomerDTO.class);
        customerDTO.setStatus(customerEntity.getStatus().getLabel());
        customerDTO.setAuthority(customerEntity.getAuthority().getLabel());
        return customerDTO;
    }

}
