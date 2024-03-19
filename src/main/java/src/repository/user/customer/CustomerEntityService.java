package src.repository.user.customer;

import src.controller.user.customer.request.CreateCustomerRequest;
import src.controller.user.customer.request.UpdateCustomerRequest;

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
