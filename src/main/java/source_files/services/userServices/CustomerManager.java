package source_files.services.userServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.requests.userRequests.AddCustomerRequest;
import source_files.services.entityServices.abstracts.CustomerEntityService;
import source_files.services.userServices.abstracts.CustomerService;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerManager implements CustomerService {
private final CustomerEntityService customerEntityService;
    @Override
    public CustomerDTO add(AddCustomerRequest addCustomerRequest) {
        return null;
    }

    @Override
    public CustomerDTO getByPhoneNumber() {
        //TODO:MAPLENCEK -MAPPER
        return null;
        //return this.customerEntityService.getByPhoneNumber();
    }
}
