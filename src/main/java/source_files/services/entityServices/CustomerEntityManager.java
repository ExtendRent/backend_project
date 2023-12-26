package source_files.services.entityServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.CustomerEntityService;

import java.util.List;

import static source_files.exception.NotFoundExceptionType.CUSTOMER_DATA_NOT_FOUND;

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

        return this.add(customerEntity);
    }

    @Override
    public CustomerEntity getById(int id) {
        return this.customerRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(CUSTOMER_DATA_NOT_FOUND, "Müşteri bulunamadı"));
    }

    @Override
    public CustomerEntity getByPhoneNumber(String phoneNumber) {
        return this.customerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new DataNotFoundException(
                        CUSTOMER_DATA_NOT_FOUND, "Bu telefon numarasına kayıtlı müşteri bulunamadı"));
    }

    @Override
    public void delete(CustomerEntity customerEntity) {
        this.customerRepository.delete(customerEntity);
    }


    @Override
    public List<CustomerEntity> getAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public List<CustomerEntity> getAllByIsDeletedFalse() {
        return this.customerRepository.findAllByIsDeletedFalse();
    }

    @Override
    public List<CustomerEntity> getAllByIsDeletedTrue() {
        return this.customerRepository.findAllByIsDeletedTrue();
    }
}
