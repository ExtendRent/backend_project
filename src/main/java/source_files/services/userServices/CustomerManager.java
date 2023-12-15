package source_files.services.userServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.data.requests.userRequests.AddCustomerRequest;
import source_files.data.types.UserType;
import source_files.services.entityServices.abstracts.CustomerEntityService;
import source_files.services.userServices.abstracts.CustomerService;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {

    private final ModelMapperService modelMapperService;
    private final CustomerEntityService customerEntityService;

    @Override
    public CustomerDTO add(AddCustomerRequest addCustomerRequest) {
        CustomerEntity customerEntity = this.modelMapperService.forRequest().map(addCustomerRequest, CustomerEntity.class);

        customerEntity.setUserType(UserType.CUSTOMER);

        return this.modelMapperService.forResponse().map(this.customerEntityService.add(customerEntity), CustomerDTO.class);
    }

    @Override
    public CustomerDTO getByPhoneNumber(String phoneNumber) {
        //TODO:MAPLENCEK -MAPPER this.customerEntityService.getByPhoneNumber(phoneNumber)
        CustomerEntity customerEntity = this.customerEntityService.getByPhoneNumber(phoneNumber);
        return this.modelMapperService.forResponse().map(customerEntity, CustomerDTO.class);
    }

    CustomerDTO convertToDto(CustomerEntity customerEntity) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setName(customerDTO.getName());
        customerDTO.setEmailAddress(customerEntity.getEmailAddress());
        customerDTO.setPhoneNumber(customerEntity.getPhoneNumber());
        customerDTO.setDrivingLicenseType(customerEntity.getDrivingLicenseType());

        return customerDTO;
    }

    CustomerEntity convertToEntity(AddCustomerRequest addCustomerRequest) {
        CustomerEntity customerEntity = new CustomerEntity();

        customerEntity.setName(addCustomerRequest.getName());
        customerEntity.setSurname(addCustomerRequest.getSurname());
        customerEntity.setEmailAddress(addCustomerRequest.getEmailAddress());
        customerEntity.setUserType(UserType.CUSTOMER);
        customerEntity.setDrivingLicenseType(addCustomerRequest.getDrivingLicenseType());
        return customerEntity;
    }
}
