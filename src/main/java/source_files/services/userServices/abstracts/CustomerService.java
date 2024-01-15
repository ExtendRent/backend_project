package source_files.services.userServices.abstracts;

import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.requests.userRequests.AddCustomerRequest;
import source_files.data.requests.userRequests.UpdateCustomerRequest;

import java.util.List;

public interface CustomerService {

    CustomerDTO add(AddCustomerRequest addCustomerRequest);

    CustomerDTO update(UpdateCustomerRequest updateCustomerRequest);

    CustomerDTO getById(int id);


    CustomerDTO getByPhoneNumber(String phoneNumber);

    CustomerDTO getByEmailAddress(String emailAddress);

    List<CustomerDTO> getAll();

    List<CustomerDTO> getAllByDeletedState(boolean isDeleted);

    void delete(int id, boolean hardDelete);

    void hardDelete(int id);

    void softDelete(int id);


}
