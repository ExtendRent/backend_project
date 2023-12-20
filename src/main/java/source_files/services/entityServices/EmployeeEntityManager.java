package source_files.services.entityServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.userEntities.EmployeeEntity;
import source_files.services.entityServices.abstracts.EmployeeEntityService;

import java.util.List;

@Service
@AllArgsConstructor
public class EmployeeEntityManager implements EmployeeEntityService {
    @Override
    public EmployeeEntity add(EmployeeEntity employeeEntity) {
        return null;
    }

    @Override
    public EmployeeEntity update(EmployeeEntity employeeEntity) {
        return null;
    }

    @Override
    public EmployeeEntity getById(int id) {
        return null;
    }

    @Override
    public EmployeeEntity getByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return null;
    }

    @Override
    public List<EmployeeEntity> getAllByIsDeletedFalse() {
        return null;
    }

    @Override
    public List<EmployeeEntity> getAllByIsDeletedTrue() {
        return null;
    }

    @Override
    public void delete(EmployeeEntity employeeEntity) {

    }
}
