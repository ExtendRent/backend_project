package source_files.services.entityServices.abstracts;

import source_files.data.models.userEntities.CustomerEntity;

import java.util.List;


public interface CustomerEntityService {

    CustomerEntity add(CustomerEntity customerEntity);

    CustomerEntity update(CustomerEntity customerEntity);

    CustomerEntity getById(int id);

    List<CustomerEntity> getAll();

    CustomerEntity getByPhoneNumber(String phoneNumber);

    List<CustomerEntity> getAllByIsDeletedFalse();

    List<CustomerEntity> getAllByIsDeletedTrue();

    void delete(CustomerEntity customerEntity);

    void hardDelete(int id);
    void softDelete(int id);

}
