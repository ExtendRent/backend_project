package source_files.services.userServices.abstracts;


import source_files.data.DTO.userDTOs.EmployeeDTO;
import source_files.data.requests.userRequests.AddEmployeeRequest;
import source_files.data.requests.userRequests.UpdateEmployeeRequest;

import java.util.List;

public interface EmployeeService {

    EmployeeDTO add(AddEmployeeRequest addEmployeeRequest);

    EmployeeDTO update(UpdateEmployeeRequest updateEmployeeRequest);

    EmployeeDTO getById(int id);

    List<EmployeeDTO> getAll();

    List<EmployeeDTO> getAllBySalaryBetween(double salary1, double salary2);

    List<EmployeeDTO> getAllByDeletedState(boolean isDeleted);

    EmployeeDTO getByPhoneNumber(String phoneNumber);

    EmployeeDTO getByEmailAddress(String emailAddress);

    void delete(int id, boolean hardDelete);

    void softDelete(int id);
}
