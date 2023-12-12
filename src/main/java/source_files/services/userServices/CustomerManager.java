package source_files.services.userServices;

import org.springframework.stereotype.Service;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.requests.userRequests.AddCustomerRequest;
import source_files.services.userServices.abstracts.CustomerService;

@Service
public class CustomerManager implements CustomerService {

    @Override
    public CustomerDTO add(AddCustomerRequest addCustomerRequest) {
        return null;
    }
}
