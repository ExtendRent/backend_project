package src.service.user.employee;


import src.controller.user.employee.requests.CreateEmployeeRequest;
import src.controller.user.employee.requests.UpdateEmployeeRequest;
import src.controller.user.employee.responses.EmployeeResponse;

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
