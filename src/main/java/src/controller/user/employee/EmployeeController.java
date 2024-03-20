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
import src.service.user.employee.EmployeeService;

import java.util.List;

import static src.controller.user.employee.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody @Valid CreateEmployeeRequest createEmployeeRequest) {
        log.info(CREATING_NEW_EMPLOYEE, createEmployeeRequest.toString());
        this.employeeService.create(createEmployeeRequest);
        log.info(EMPLOYEE_SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<EmployeeResponse>> updateEmployee(@RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest) {
        log.info(UPDATING_EMPLOYEE, updateEmployeeRequest.toString());
        EmployeeResponse updatedEmployee = this.employeeService.update(updateEmployeeRequest);
        log.info(EMPLOYEE_UPDATED, updatedEmployee.toString());
        return new ResponseEntity<>(TResponse.<EmployeeResponse>tResponseBuilder()
                .response(updatedEmployee)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<EmployeeResponse>>> getAll() {
        log.info(GETTING_ALL_EMPLOYEES);
        List<EmployeeResponse> employees = this.employeeService.getAll();
        log.info(RETRIEVED_ALL_EMPLOYEES, employees.size());
        return new ResponseEntity<>(TResponse.<List<EmployeeResponse>>tResponseBuilder()
                .response(employees)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/count/{isDeleted}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable boolean isDeleted) {
        log.info(GETTING_EMPLOYEE_COUNT_BY_DELETED_STATE, isDeleted);
        int count = this.employeeService.getCountByDeletedState(isDeleted);
        log.info(RETRIEVED_EMPLOYEE_COUNT_BY_DELETED_STATE, count);
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(count)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<EmployeeResponse>> getById(@PathVariable int id) {
        log.info(GETTING_EMPLOYEE_BY_ID, id);
        EmployeeResponse employee = this.employeeService.getById(id);
        log.info(RETRIEVED_EMPLOYEE_BY_ID, id);
        return new ResponseEntity<>(TResponse.<EmployeeResponse>tResponseBuilder()
                .response(employee)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<TResponse<EmployeeResponse>> getByPhoneNumber(@PathVariable String phoneNumber) {
        log.info(GETTING_EMPLOYEE_BY_PHONE_NUMBER, phoneNumber);
        EmployeeResponse employee = this.employeeService.getByPhoneNumber(phoneNumber);
        log.info(RETRIEVED_EMPLOYEE_BY_PHONE_NUMBER, phoneNumber);
        return new ResponseEntity<>(TResponse.<EmployeeResponse>tResponseBuilder()
                .response(employee)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = {"startSalary", "endSalary"})
    public ResponseEntity<TResponse<List<EmployeeResponse>>> getAllBySalaryBetween(
            @RequestParam(name = "startSalary", required = false) Double startSalary,
            @RequestParam(name = "endSalary", required = false) Double endSalary) {
        log.info(GETTING_EMPLOYEES_BY_SALARY_BETWEEN, startSalary, endSalary);
        List<EmployeeResponse> employees = this.employeeService.getAllBySalaryBetween(startSalary, endSalary);
        log.info(RETRIEVED_EMPLOYEES_BY_SALARY_BETWEEN, employees.size());
        return new ResponseEntity<>(TResponse.<List<EmployeeResponse>>tResponseBuilder()
                .response(employees)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<EmployeeResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        log.info(GETTING_EMPLOYEES_BY_DELETED_STATE, isDeleted);
        List<EmployeeResponse> employees = this.employeeService.getAllByDeletedState(isDeleted);
        log.info(RETRIEVED_EMPLOYEES_BY_DELETED_STATE, employees.size());
        return new ResponseEntity<>(TResponse.<List<EmployeeResponse>>tResponseBuilder()
                .response(employees)
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        log.info(DELETING_EMPLOYEE_WITH_ID, id, isHardDelete);
        this.employeeService.delete(id, isHardDelete);
        log.info(EMPLOYEE_DELETED_SUCCESSFULLY_WITH_ID, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
