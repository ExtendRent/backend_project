package source_files.services.entityServices.abstracts;

import source_files.data.models.userEntities.CustomerEntity;


public interface CustomerEntityService {

    CustomerEntity add(CustomerEntity customerEntity);

    CustomerEntity update(CustomerEntity customerEntity);

    CustomerEntity getById(int id);

    CustomerEntity getByPhoneNumber(String phoneNumber);

    void delete(CustomerEntity customerEntity);


}
