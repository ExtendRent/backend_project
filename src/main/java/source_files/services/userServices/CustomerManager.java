package source_files.services.userServices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.models.imageEntities.UserImageEntity;
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
import java.util.stream.Collectors;

import static source_files.data.enums.defaultDataEnums.Status.DefaultUserStatus.PENDING_VERIFYING;
import static source_files.data.enums.types.userTypes.UserRole.CUSTOMER;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {

    private final ModelMapperService modelMapperService;
    private final CustomerRepository customerRepository;
    private final CustomerBusinessRules rules;
    private final CustomerEntityService entityService;
    private final PasswordEncoder passwordEncoder;
    private final UserImageService userImageService;

    @Override
    public void create(CreateCustomerRequest createCustomerRequest) {
        try {
            CustomerEntity customerEntity = modelMapperService.forRequest()
                    .map(rules.checkCreateCustomerRequest(
                            rules.fixCreateCustomerRequest(createCustomerRequest)), CustomerEntity.class
                    );
            customerEntity.setPassword(passwordEncoder.encode(customerEntity.getPassword()));
            customerEntity.setAuthority(CUSTOMER);
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
        customerEntity = modelMapperService.forRequest()
                .map(rules.checkUpdateCustomerRequest(
                        rules.fixUpdateCustomerRequest(updateCustomerRequest)), CustomerEntity.class
                );

        return modelMapperService.forResponse().map(entityService.create(customerEntity), CustomerDTO.class);
    }

    @Override
    public CustomerDTO getById(int id) {
        return modelMapperService.forResponse().map(entityService.getById(id), CustomerDTO.class);

    }

    @Override
    public CustomerDTO getByPhoneNumber(String phoneNumber) {
        return modelMapperService.forResponse().map(entityService.getByPhoneNumber(phoneNumber), CustomerDTO.class);
    }

    @Override
    public CustomerDTO getByEmailAddress(String emailAddress) {
        return modelMapperService.forResponse()
                .map(entityService.getByEmailAddress(emailAddress), CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getAll() {
        return rules.checkDataList(entityService.getAll())
                .stream().map(customer -> modelMapperService.forResponse()
                        .map(customer, CustomerDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getAllByDeletedState(boolean isDeleted) {
        return rules.checkDataList(entityService.getAllByDeletedState(isDeleted))
                .stream().map(customerEntity -> modelMapperService.forResponse()
                        .map(customerEntity, CustomerDTO.class)).toList();
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
    public void softDelete(int id) {
        CustomerEntity customerEntity = entityService.getById(id);
        customerEntity.setIsDeleted(true);
        customerEntity.setDeletedAt(LocalDateTime.now());
        customerRepository.save(customerEntity);
    }

}
