package source_files.services.entityServices.userEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import source_files.data.models.userEntities.EmployeeEntity;
import source_files.dataAccess.userRepositories.EmployeeRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.userAbstract.EmployeeEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.EMPLOYEE_DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class EmployeeEntityManager implements EmployeeEntityService {
    private final EmployeeRepository repository;

    @Override
    public EmployeeEntity create(EmployeeEntity employeeEntity) {
        employeeEntity.setId(0);
        return repository.save(employeeEntity);
    }

    @Override
    public EmployeeEntity update(EmployeeEntity employeeEntity) {

        return create(employeeEntity);
    }

    @Override
    @Transactional
    public EmployeeEntity getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(EMPLOYEE_DATA_NOT_FOUND));
    }

    @Override
    public List<EmployeeEntity> getAllBySalaryBetween(double salary1, double salary2) {
        return repository.findAllBySalaryBetween(salary1, salary2);
    }

    @Override
    public List<EmployeeEntity> getAll() {

        return repository.findAll();
    }

    @Override
    public List<EmployeeEntity> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted);
    }


    @Override
    public void delete(EmployeeEntity employeeEntity) {

        repository.delete(employeeEntity);
    }

    @Override
    public int getCountByDeletedState(boolean isDeleted) {
        return repository.countByIsDeleted(isDeleted);
    }

    @Override
    public EmployeeEntity getByPhoneNumber(String phoneNumber) {
        return repository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new DataNotFoundException(EMPLOYEE_DATA_NOT_FOUND)
        );
    }

    @Override
    public EmployeeEntity getByEmailAddress(String emailAddress) {
        return repository.findByEmailAddress(emailAddress).orElseThrow(() -> new DataNotFoundException(
                EMPLOYEE_DATA_NOT_FOUND));
    }
}
