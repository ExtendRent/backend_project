package source_files.services.entityServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.services.entityServices.abstracts.CustomerEntityService;

import java.util.List;

@AllArgsConstructor
@Service
public class CustomerEntityManager implements CustomerEntityService {

    private final CustomerRepository customerRepository;


    @Override
    public CustomerEntity add(CustomerEntity customerEntity) {
        return this.customerRepository.save(customerEntity);
    }

    @Override
    public CustomerEntity update(CustomerEntity customerEntity) {
        return null;
    }

    @Override
    public CustomerEntity getById(int id) {
        return this.customerRepository.findById(id).orElseThrow();
    }

    @Override
    public CustomerEntity getByPhoneNumber(String phoneNumber) {
        return this.customerRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public void delete(CustomerEntity customerEntity) {
        this.customerRepository.delete(customerEntity);
    }

    @Override
    public List<CustomerEntity> getAll() {
        return this.customerRepository.findAll();
    }
}
