package source_files.services.entityServices.abstracts;

import source_files.data.models.userEntities.CustomerEntity;
import source_files.data.models.userEntities.EmployeeEntity;

import java.util.List;

public interface EmployeeEntityService {

    EmployeeEntity add(EmployeeEntity employeeEntity);

    EmployeeEntity update(EmployeeEntity employeeEntity);

    EmployeeEntity getById(int id);

    EmployeeEntity getByPhoneNumber(String phoneNumber);

    List<EmployeeEntity> getAll();

    List<EmployeeEntity> getAllByIsDeletedFalse();

    List<EmployeeEntity> getAllByIsDeletedTrue();

    void delete(EmployeeEntity employeeEntity);

}
