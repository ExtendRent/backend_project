package source_files.services.entityServices.abstracts;

import source_files.data.models.userEntities.CustomerEntity;


public interface CustomerEntityService {

    CustomerEntity add(CustomerEntity customerEntity);

    CustomerEntity getByPhoneNumber(String phoneNumber);



}
