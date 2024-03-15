package source_files.services.user.abstracts;

import source_files.controllers.paperWork.dtos.RentalDTO;
import source_files.controllers.user.dtos.CustomerDTO;
import source_files.controllers.user.requests.customer.CreateCustomerRequest;
import source_files.controllers.user.requests.customer.UpdateCustomerRequest;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;

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

    int getCountByDeletedState(boolean isDeleted);

    int getCountByStatus(String status);
}
