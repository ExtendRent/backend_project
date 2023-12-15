package source_files.services.userServices.abstracts;

import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.requests.userRequests.AddCustomerRequest;

public interface CustomerService {

    CustomerDTO add(AddCustomerRequest addCustomerRequest);

    CustomerDTO getByPhoneNumber(String phoneNumber);
    
}
