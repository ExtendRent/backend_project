package source_files.services.userServices.abstracts;

import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.requests.userRequests.AddCustomerRequest;

import java.util.List;

public interface CustomerService {

    CustomerDTO add(AddCustomerRequest addCustomerRequest);


    CustomerDTO getById(int id);


    CustomerDTO getByPhoneNumber(String phoneNumber);

    List<CustomerDTO> getAll();


    List<CustomerDTO> getAllByIsDeletedFalse();

    List<CustomerDTO> getAllByIsDeletedTrue();

    void delete(int id, boolean hardDelete);

    void hardDelete(int id);

    void softDelete(int id);


}
