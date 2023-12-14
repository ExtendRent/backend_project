package source_files.services.entityServices.abstracts;

import source_files.data.models.userEntities.CustomerEntity;


public interface CustomerEntityService {
    CustomerEntity getByPhoneNumber(int phoneNumber);
}
