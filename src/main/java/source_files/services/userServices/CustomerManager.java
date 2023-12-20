package source_files.services.userServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.data.requests.userRequests.AddCustomerRequest;
import source_files.data.types.UserType;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.userServices.abstracts.CustomerService;

import java.util.List;

import static source_files.exception.NotFoundExceptionType.CUSTOMER_DATA_NOT_FOUND;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {

    private final ModelMapperService modelMapperService;
    private final CustomerRepository customerRepository;

    @Override
    public CustomerDTO add(AddCustomerRequest addCustomerRequest) {
        CustomerEntity customerEntity = this.modelMapperService.forRequest().map(addCustomerRequest, CustomerEntity.class);

        customerEntity.setUserType(UserType.CUSTOMER);

        return this.modelMapperService.forResponse().map(this.customerRepository.save(customerEntity), CustomerDTO.class);
    }

    @Override
    public CustomerDTO getById(int id) {
        return this.modelMapperService.forResponse()
                .map(this.customerRepository.findById(id)
                        .orElseThrow(() -> new DataNotFoundException(CUSTOMER_DATA_NOT_FOUND, "Müşteri bulunamadı")), CustomerDTO.class);
    }

    @Override
    public CustomerDTO getByPhoneNumber(String phoneNumber) {
        //TODO repository kullanırsak bu şekilde sürekli hata belirtmek zorundayız.
        CustomerEntity customerEntity = this.customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new DataNotFoundException(CUSTOMER_DATA_NOT_FOUND, "Bu telefon numarasına kayıtlı müşteri bulunamadı"));

        return this.modelMapperService.forResponse().map(customerEntity, CustomerDTO.class);
    }

    @Override
    public List<CustomerDTO> getAll() {
        return this.customerRepository.findAll().stream()
                .map(customerEntity -> this.modelMapperService.forResponse().map(customerEntity, CustomerDTO.class)).toList();
    }

    @Override
    public List<CustomerDTO> getAllByIsDeletedFalse() {
        return this.customerRepository.findAllByIsDeletedFalse()
                .stream().map(customerEntity -> modelMapperService.forResponse().map(customerEntity, CustomerDTO.class)).toList();
    }

    @Override
    public List<CustomerDTO> getAllByIsDeletedTrue() {
        return this.customerRepository.findAllByIsDeletedTrue()
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
        this.customerRepository.delete(this.customerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(CUSTOMER_DATA_NOT_FOUND, "böyle bir müşteri bulunamadı")));
    }

    @Override
    public void softDelete(int id) {
        CustomerEntity customerEntity = this.customerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(CUSTOMER_DATA_NOT_FOUND, "böyle bir müşteri bulunamadı"));
        customerEntity.setIsDeleted(true);
        this.customerRepository.save(customerEntity);
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
