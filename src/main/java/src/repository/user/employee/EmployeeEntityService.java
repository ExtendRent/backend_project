package src.repository.user.employee;


import src.controller.user.employee.request.CreateEmployeeRequest;
import src.controller.user.employee.request.UpdateEmployeeRequest;

import java.util.List;

public interface EmployeeEntityService {

    EmployeeEntity create(CreateEmployeeRequest createEmployeeRequest);

    EmployeeEntity update(UpdateEmployeeRequest updateEmployeeRequest);

    EmployeeEntity update(EmployeeEntity employeeEntity);

    EmployeeEntity getById(int id);

    EmployeeEntity getByPhoneNumber(String phoneNumber);

    EmployeeEntity getByEmailAddress(String emailAddress);

    List<EmployeeEntity> getAllBySalaryBetween(double salary1, double salary2);

    List<EmployeeEntity> getAll();

    List<EmployeeEntity> getAllByDeletedState(boolean isDeleted);

    void delete(EmployeeEntity userEntity);

    int getCountByDeletedState(boolean isDeleted);
}
