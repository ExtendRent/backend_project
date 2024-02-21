package source_files.services.entityServices.abstracts.userAbstract;

import source_files.data.models.userEntities.CustomerEntity;

import java.util.List;


public interface CustomerEntityService {

    CustomerEntity create(CustomerEntity customerEntity);

    CustomerEntity update(CustomerEntity customerEntity);

    CustomerEntity getById(int id);

    List<CustomerEntity> getAll();

    CustomerEntity getByEmailAddress(String emailAddress);

    List<CustomerEntity> getAllByDeletedState(boolean isDeleted);

    int getCountByDeletedState(boolean isDeleted);

    int getCountByStatus(String status);

    void delete(CustomerEntity customerEntity);


}
