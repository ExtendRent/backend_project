package src.services.user.customer;

import src.controllers.paperwork.responses.RentalResponse;
import src.controllers.user.requests.customer.CreateCustomerRequest;
import src.controllers.user.requests.customer.UpdateCustomerRequest;
import src.controllers.user.responses.CustomerResponse;
import src.data.models.paperwork_entities.rentalEntities.RentalEntity;

import java.util.List;

public interface CustomerService {

    void create(CreateCustomerRequest createCustomerRequest);

    CustomerResponse update(UpdateCustomerRequest updateCustomerRequest);

    CustomerResponse getById(int id);

    CustomerResponse getByEmailAddress(String emailAddress);

    List<CustomerResponse> getAll();

    List<CustomerResponse> getAllByDeletedState(boolean isDeleted);

    void addRental(int customerId, RentalEntity rentalEntity);

    void removeRental(int customerId, RentalEntity rentalEntity);

    void delete(int id, boolean hardDelete);

    List<RentalResponse> getRentalHistory(int customerId);

    void softDelete(int id);

    int getCountByDeletedState(boolean isDeleted);

    int getCountByStatus(String status);
}
