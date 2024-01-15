package source_files.services.entityServices.userEntityManagers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.userEntities.EmployeeEntity;
import source_files.dataAccess.userRepositories.EmployeeRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.userAbstract.EmployeeEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.EMPLOYEE_DATA_NOT_FOUND;

@Service
@AllArgsConstructor
public class EmployeeEntityManager implements EmployeeEntityService {
    private final EmployeeRepository employeeRepository;

    @Override
    public EmployeeEntity add(EmployeeEntity employeeEntity) {

        return this.employeeRepository.save(employeeEntity);
    }

    @Override
    public EmployeeEntity update(EmployeeEntity employeeEntity) {

        return this.add(employeeEntity);
    }

    @Override
    public EmployeeEntity getById(int id) {
        return this.employeeRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException(EMPLOYEE_DATA_NOT_FOUND, "Çalışan bulunamadı"));
    }

    @Override
    public List<EmployeeEntity> getAllBySalaryBetween(double salary1, double salary2) {
        return this.employeeRepository.findAllBySalaryBetween(salary1, salary2);
    }

    @Override
    public List<EmployeeEntity> getAll() {

        return this.employeeRepository.findAll();
    }

    @Override
    public List<EmployeeEntity> getAllByDeletedState(boolean isDeleted) {
        return this.employeeRepository.findAllByIsDeleted(isDeleted);
    }


    @Override
    public void delete(EmployeeEntity employeeEntity) {

        this.employeeRepository.delete(employeeEntity);
    }

    @Override
    public EmployeeEntity getByPhoneNumber(String phoneNumber) {
        return this.employeeRepository.findByPhoneNumber(phoneNumber).orElseThrow(
                () -> new DataNotFoundException(EMPLOYEE_DATA_NOT_FOUND, "Bu telefon numarasına kayıtlı çalışan bulunamadı")
        );
    }

    @Override
    public EmployeeEntity getByEmailAddress(String emailAddress) {
        return this.employeeRepository.findByEmailAddress(emailAddress).orElseThrow(() -> new DataNotFoundException(
                EMPLOYEE_DATA_NOT_FOUND, "Bu email adresine kayıtlı çalışan bulunamadı"
        ));
    }
}
