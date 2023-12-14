package source_files.services.entityServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.services.entityServices.abstracts.CustomerEntityService;

@AllArgsConstructor
@Service
public class CustomerEntityManager implements CustomerEntityService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerEntity getByPhoneNumber(int phoneNumber) {
        return this.customerRepository.findByPhoneNumber(phoneNumber);
    }
}
