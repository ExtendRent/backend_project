package src.services.user.employee;


import src.controllers.user.requests.employee.CreateEmployeeRequest;
import src.controllers.user.requests.employee.UpdateEmployeeRequest;
import src.controllers.user.responses.EmployeeResponse;

import java.util.List;

public interface EmployeeService {

    void create(CreateEmployeeRequest createEmployeeRequest);

    EmployeeResponse update(UpdateEmployeeRequest updateEmployeeRequest);

    EmployeeResponse getById(int id);

    List<EmployeeResponse> getAll();

    List<EmployeeResponse> getAllBySalaryBetween(double salary1, double salary2);

    List<EmployeeResponse> getAllByDeletedState(boolean isDeleted);

    EmployeeResponse getByPhoneNumber(String phoneNumber);

    EmployeeResponse getByEmailAddress(String emailAddress);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);

    int getCountByDeletedState(boolean isDeleted);
}
