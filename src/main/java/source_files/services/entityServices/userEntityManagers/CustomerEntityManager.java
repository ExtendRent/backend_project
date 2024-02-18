package source_files.services.entityServices.userEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.userAbstract.CustomerEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.CUSTOMER_DATA_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CustomerEntityManager implements CustomerEntityService {

    private final CustomerRepository customerRepository;

    @Override
    public CustomerEntity create(CustomerEntity customerEntity) {
        customerEntity.setId(0);
        return this.customerRepository.save(customerEntity);
    }

    @Override
    public CustomerEntity update(CustomerEntity customerEntity) {
        return customerRepository.save(customerEntity);
    }

    @Override
    public CustomerEntity getById(int id) {
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(CUSTOMER_DATA_NOT_FOUND, "Müşteri bulunamadı"));
    }

    @Override
    public CustomerEntity getByEmailAddress(String emailAddress) {
        return this.customerRepository.findByEmailAddress(emailAddress).orElseThrow(() -> new DataNotFoundException(
                CUSTOMER_DATA_NOT_FOUND, "Bu email adresine kayıtlı müşteri bulunamadı"
        ));
    }

    @Override
    public List<CustomerEntity> getAllByDeletedState(boolean isDeleted) {
        return this.customerRepository.findAllByIsDeleted(isDeleted);
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
