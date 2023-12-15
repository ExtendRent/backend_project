package source_files.services.entityServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.services.entityServices.abstracts.CustomerEntityService;

@AllArgsConstructor
@Service
public class CustomerEntityManager implements CustomerEntityService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerEntity add(CustomerEntity customerEntity) {
        return this.customerRepository.save(customerEntity);
    }

    @Override
    public CustomerEntity getByPhoneNumber(String phoneNumber) {
        return this.customerRepository.findByPhoneNumber(phoneNumber);
    }
}
