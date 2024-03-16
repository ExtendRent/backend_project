package src.services.user.customer;

import src.controllers.user.requests.customer.CreateCustomerRequest;
import src.controllers.user.requests.customer.UpdateCustomerRequest;
import src.data.models.user_entities.CustomerEntity;

import java.util.List;


public interface CustomerEntityService {

    CustomerEntity create(CreateCustomerRequest createCustomerRequest);

    CustomerEntity update(UpdateCustomerRequest updateCustomerRequest);

    CustomerEntity update(CustomerEntity customerEntity);

    CustomerEntity getById(int id);

    List<CustomerEntity> getAll();

    CustomerEntity getByEmailAddress(String emailAddress);

    List<CustomerEntity> getAllByDeletedState(boolean isDeleted);

    int getCountByDeletedState(boolean isDeleted);

    int getCountByStatus(String status);

    void delete(CustomerEntity customerEntity);


}
