package source_files.services.userServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.data.requests.userRequests.AddCustomerRequest;
import source_files.data.requests.userRequests.UpdateCustomerRequest;
import source_files.data.types.UserType;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.BusinessRules.CustomerBusinessRules;
import source_files.services.entityServices.abstracts.CustomerEntityService;
import source_files.services.userServices.abstracts.CustomerService;

import java.util.List;
import java.util.stream.Collectors;

import static source_files.exception.NotFoundExceptionType.CUSTOMER_DATA_NOT_FOUND;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {

    private final ModelMapperService modelMapperService;
    private final CustomerRepository customerRepository;
    private final CustomerBusinessRules customerBusinessRules;
    private final CustomerEntityService customerEntityService;

    @Override
    public CustomerDTO add(AddCustomerRequest addCustomerRequest) {
        CustomerEntity customerEntity = modelMapperService.forRequest()
                        .map(customerBusinessRules.checkAddCustomerRequest(
                                customerBusinessRules.fixAddCustomerRequest(addCustomerRequest)),CustomerEntity.class
                        );

        customerEntity.setUserType(UserType.CUSTOMER);

        return this.modelMapperService.forResponse().map(this.customerEntityService.add(customerEntity), CustomerDTO.class);
    }

    @Override
    public CustomerDTO update(UpdateCustomerRequest updateCustomerRequest) {
        CustomerEntity customerEntity = modelMapperService.forRequest()
                .map(customerBusinessRules.checkUpdateCustomerRequest(
                        customerBusinessRules.fixUpdateCustomerRequest(updateCustomerRequest)),CustomerEntity.class
                );

        customerEntity.setUserType(UserType.CUSTOMER);

        return this.modelMapperService.forResponse().map(this.customerEntityService.add(customerEntity), CustomerDTO.class);
    }

    @Override
    public CustomerDTO getById(int id) {
        return this.modelMapperService.forResponse().map(customerEntityService.getById(id),CustomerDTO.class);

    }

    @Override
    public CustomerDTO getByPhoneNumber(String phoneNumber) {
        return this.modelMapperService.forResponse().map(customerEntityService.getByPhoneNumber(phoneNumber), CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getAll() {
        return customerBusinessRules.checkDataList(customerEntityService.getAll())
                .stream().map(customer -> modelMapperService.forResponse()
                        .map(customer,CustomerDTO.class )).collect(Collectors.toList());
        }

    @Override
    public List<CustomerDTO> getAllByIsDeletedFalse() {
        return customerBusinessRules.checkDataList(this.customerRepository.findAllByIsDeletedFalse())
                .stream().map(customerEntity -> modelMapperService.forResponse().map(customerEntity, CustomerDTO.class)).toList();
    }

    @Override
    public List<CustomerDTO> getAllByIsDeletedTrue() {
        return customerBusinessRules.checkDataList(this.customerRepository.findAllByIsDeletedTrue())
                .stream().map(customerEntity -> modelMapperService.forResponse().map(customerEntity, CustomerDTO.class)).toList();
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            this.hardDelete(id);
        } else {
            this.softDelete(id);
        }
    }

    @Override
    public void hardDelete(int id) {
        customerEntityService.hardDelete(id);
    }

    @Override
    public void softDelete(int id) {
        customerEntityService.softDelete(id);
    }

}
