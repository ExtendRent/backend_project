package source_files.services.entityServices.userEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.enums.defaultDataEnums.Status.DefaultUserStatus;
import source_files.data.models.userEntities.CustomerEntity;
import source_files.dataAccess.userRepositories.CustomerRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.userAbstract.CustomerEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.CUSTOMER_DATA_NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CustomerEntityManager implements CustomerEntityService {

    private final CustomerRepository repository;

    @Override
    public CustomerEntity create(CustomerEntity customerEntity) {
        customerEntity.setId(0);
        return repository.save(customerEntity);
    }

    @Override
    public CustomerEntity update(CustomerEntity customerEntity) {
        return repository.save(customerEntity);
    }

    @Override
    public CustomerEntity getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(CUSTOMER_DATA_NOT_FOUND, "Müşteri bulunamadı"));
    }

    @Override
    public CustomerEntity getByEmailAddress(String emailAddress) {
        return repository.findByEmailAddress(emailAddress).orElseThrow(() -> new DataNotFoundException(
                CUSTOMER_DATA_NOT_FOUND, "Bu email adresine kayıtlı müşteri bulunamadı"
        ));
    }

    @Override
    public List<CustomerEntity> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public int getCountByDeletedState(boolean isDeleted) {
        return repository.countByIsDeleted(isDeleted);
    }

    @Override
    public int getCountByStatus(String status) {
        return repository.countByStatus(DefaultUserStatus.valueOf(status.toUpperCase().trim()));
    }

    @Override
    public void delete(CustomerEntity customerEntity) {
        repository.delete(customerEntity);
    }


    @Override
    public List<CustomerEntity> getAll() {
        return repository.findAll();
    }


}
