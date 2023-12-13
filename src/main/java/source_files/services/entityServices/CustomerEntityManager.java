package source_files.services.entityServices;

import lombok.AllArgsConstructor;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.services.entityServices.abstracts.CustomerEntityService;

import java.util.List;
@AllArgsConstructor
public class CustomerEntityManager implements CustomerEntityService {

private final CustomerRepository customerRepository;
    @Override
    public CustomerEntity getByPhoneNumber() {
        return this.customerRepository.findByPhoneNumber();
    }
}
