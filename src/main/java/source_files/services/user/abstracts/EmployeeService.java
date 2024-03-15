package source_files.services.user.abstracts;


import source_files.controllers.user.dtos.EmployeeDTO;
import source_files.controllers.user.requests.employee.CreateEmployeeRequest;
import source_files.controllers.user.requests.employee.UpdateEmployeeRequest;

import java.util.List;

public interface EmployeeService {

    void create(CreateEmployeeRequest createEmployeeRequest);

    EmployeeDTO update(UpdateEmployeeRequest updateEmployeeRequest);

    EmployeeDTO getById(int id);

    List<EmployeeDTO> getAll();

    List<EmployeeDTO> getAllBySalaryBetween(double salary1, double salary2);

    List<EmployeeDTO> getAllByDeletedState(boolean isDeleted);

    EmployeeDTO getByPhoneNumber(String phoneNumber);

    EmployeeDTO getByEmailAddress(String emailAddress);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);

    int getCountByDeletedState(boolean isDeleted);
}
