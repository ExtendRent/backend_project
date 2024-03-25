package src.controller.user.employee;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.user.employee.request.CreateEmployeeRequest;
import src.controller.user.employee.request.UpdateEmployeeRequest;
import src.controller.user.employee.response.EmployeeResponse;
import src.core.rest.BaseController;
import src.service.user.employee.EmployeeService;

import java.util.List;

import static src.controller.user.employee.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController extends BaseController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody @Valid CreateEmployeeRequest createEmployeeRequest) {
        log.info(CREATING_NEW_EMPLOYEE, createEmployeeRequest.toString());
        employeeService.create(createEmployeeRequest);
        log.info(EMPLOYEE_SUCCESSFULLY_CREATED);
        return answer(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<EmployeeResponse>> updateEmployee(@RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest) {
        log.info(UPDATING_EMPLOYEE, updateEmployeeRequest.toString());
        EmployeeResponse response = employeeService.update(updateEmployeeRequest);
        log.info(EMPLOYEE_UPDATED, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<EmployeeResponse>>> getAll() {
        log.info(GETTING_ALL_EMPLOYEES);
        List<EmployeeResponse> response = employeeService.getAll();
        log.info(RETRIEVED_ALL_EMPLOYEES, response.size());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/count/{isDeleted}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable boolean isDeleted) {
        log.info(GETTING_EMPLOYEE_COUNT_BY_DELETED_STATE, isDeleted);
        int response = employeeService.getCountByDeletedState(isDeleted);
        log.info(RETRIEVED_EMPLOYEE_COUNT_BY_DELETED_STATE, response);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<EmployeeResponse>> getById(@PathVariable int id) {
        log.info(GETTING_EMPLOYEE_BY_ID, id);
        EmployeeResponse response = employeeService.getById(id);
        log.info(RETRIEVED_EMPLOYEE_BY_ID, id);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<TResponse<EmployeeResponse>> getByPhoneNumber(@PathVariable String phoneNumber) {
        log.info(GETTING_EMPLOYEE_BY_PHONE_NUMBER, phoneNumber);
        EmployeeResponse response = employeeService.getByPhoneNumber(phoneNumber);
        log.info(RETRIEVED_EMPLOYEE_BY_PHONE_NUMBER, phoneNumber);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping(params = {"startSalary", "endSalary"})
    public ResponseEntity<TResponse<List<EmployeeResponse>>> getAllBySalaryBetween(
            @RequestParam(name = "startSalary", required = false) Double startSalary,
            @RequestParam(name = "endSalary", required = false) Double endSalary) {
        log.info(GETTING_EMPLOYEES_BY_SALARY_BETWEEN, startSalary, endSalary);
        List<EmployeeResponse> response = employeeService.getAllBySalaryBetween(startSalary, endSalary);
        log.info(RETRIEVED_EMPLOYEES_BY_SALARY_BETWEEN, response.size());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<EmployeeResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        log.info(GETTING_EMPLOYEES_BY_DELETED_STATE, isDeleted);
        List<EmployeeResponse> response = employeeService.getAllByDeletedState(isDeleted);
        log.info(RETRIEVED_EMPLOYEES_BY_DELETED_STATE, response.size());
        return answer(response, HttpStatus.OK);
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        log.info(DELETING_EMPLOYEE_WITH_ID, id, isHardDelete);
        employeeService.delete(id, isHardDelete);
        log.info(EMPLOYEE_DELETED_SUCCESSFULLY_WITH_ID, id);
        return answer(HttpStatus.NO_CONTENT);
    }

}
