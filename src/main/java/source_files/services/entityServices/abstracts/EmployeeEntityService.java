package source_files.services.entityServices.abstracts;


import source_files.data.models.userEntities.EmployeeEntity;

import java.util.List;

public interface EmployeeEntityService {

    EmployeeEntity add(EmployeeEntity employeeEntity);

    EmployeeEntity update(EmployeeEntity employeeEntity);

    EmployeeEntity getById(int id);

    List<EmployeeEntity> getAllBySalaryBetween(double salary1, double salary2);

    List<EmployeeEntity> getAll();

    List<EmployeeEntity> getAllByIsDeletedFalse();

    List<EmployeeEntity> getAllByIsDeletedTrue();

    void delete(EmployeeEntity employeeEntity);

    EmployeeEntity getByPhoneNumber(String phoneNumber);

}
