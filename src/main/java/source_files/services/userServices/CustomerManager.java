package source_files.services.userServices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.data.requests.userRequests.CreateCustomerRequest;
import source_files.data.requests.userRequests.UpdateCustomerRequest;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.services.BusinessRules.userBusinessRuless.CustomerBusinessRules;
import source_files.services.entityServices.abstracts.userAbstract.CustomerEntityService;
import source_files.services.userServices.abstracts.CustomerService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static source_files.data.enums.Status.DefaultUserStatus.PENDING_VERIFYING;
import static source_files.data.enums.types.userTypes.UserRole.CUSTOMER;

@Service
@RequiredArgsConstructor
public class CustomerManager implements CustomerService {

    private final ModelMapperService modelMapperService;
    private final CustomerRepository customerRepository;
    private final CustomerBusinessRules customerBusinessRules;
    private final CustomerEntityService customerEntityService;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void create(CreateCustomerRequest createCustomerRequest) {

        CustomerEntity customerEntity = modelMapperService.forRequest()
                .map(customerBusinessRules.checkCreateCustomerRequest(
                        customerBusinessRules.fixCreateCustomerRequest(createCustomerRequest)), CustomerEntity.class
                );
        customerEntity.setPassword(passwordEncoder.encode(customerEntity.getPassword()));
        customerEntity.setAuthority(CUSTOMER);
        customerEntity.setStatus(PENDING_VERIFYING);
        this.customerEntityService.create(customerEntity);
    }

    @Override
    public CustomerDTO update(UpdateCustomerRequest updateCustomerRequest) {
        CustomerEntity customerEntity = modelMapperService.forRequest()
                .map(customerBusinessRules.checkUpdateCustomerRequest(
                        customerBusinessRules.fixUpdateCustomerRequest(updateCustomerRequest)), CustomerEntity.class
                );


        return this.modelMapperService.forResponse().map(this.customerEntityService.create(customerEntity), CustomerDTO.class);
    }

    @Override
    public CustomerDTO getById(int id) {
        return this.modelMapperService.forResponse().map(customerEntityService.getById(id), CustomerDTO.class);

    }

    @Override
    public CustomerDTO getByPhoneNumber(String phoneNumber) {
        return this.modelMapperService.forResponse().map(customerEntityService.getByPhoneNumber(phoneNumber), CustomerDTO.class);
    }

    @Override
    public CustomerDTO getByEmailAddress(String emailAddress) {
        return this.modelMapperService.forResponse()
                .map(customerEntityService.getByEmailAddress(emailAddress), CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getAll() {
        return customerBusinessRules.checkDataList(customerEntityService.getAll())
                .stream().map(customer -> modelMapperService.forResponse()
                        .map(customer, CustomerDTO.class)).collect(Collectors.toList());
    }

    @Override
    public List<CustomerDTO> getAllByDeletedState(boolean isDeleted) {
        return customerBusinessRules.checkDataList(customerEntityService.getAllByDeletedState(isDeleted))
                .stream().map(customerEntity -> modelMapperService.forResponse()
                        .map(customerEntity, CustomerDTO.class)).toList();
    }


    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            this.customerEntityService.delete(customerEntityService.getById(id));
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void softDelete(int id) {
        CustomerEntity customerEntity = this.customerEntityService.getById(id);
        customerEntity.setIsDeleted(true);
        customerEntity.setDeletedAt(LocalDateTime.now());
        customerRepository.save(customerEntity);
    }

}
