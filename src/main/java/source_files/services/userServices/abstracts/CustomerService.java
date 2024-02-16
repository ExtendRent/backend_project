package source_files.services.userServices.abstracts;

import source_files.data.DTO.paperWorkDTOs.RentalDTO;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.requests.userRequests.CreateCustomerRequest;
import source_files.data.requests.userRequests.UpdateCustomerRequest;

import java.util.List;

public interface CustomerService {

    void create(CreateCustomerRequest createCustomerRequest);

    CustomerDTO update(UpdateCustomerRequest updateCustomerRequest);

    CustomerDTO getById(int id);

    CustomerDTO getByEmailAddress(String emailAddress);

    List<CustomerDTO> getAll();

    List<CustomerDTO> getAllByDeletedState(boolean isDeleted);

    void addRental(int customerId, RentalEntity rentalEntity);

    void removeRental(int customerId, RentalEntity rentalEntity);

    void delete(int id, boolean hardDelete);

    List<RentalDTO> getRentalHistory(int customerId);

    void softDelete(int id);


}
