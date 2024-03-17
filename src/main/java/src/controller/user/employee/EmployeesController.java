package src.controller.user.employee;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.user.employee.requests.CreateEmployeeRequest;
import src.controller.user.employee.requests.UpdateEmployeeRequest;
import src.controller.user.employee.responses.EmployeeResponse;
import src.service.user.employee.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
@RequiredArgsConstructor
@Validated

public class EmployeesController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody @Valid CreateEmployeeRequest createEmployeeRequest) {
        this.employeeService.create(createEmployeeRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<EmployeeResponse>> updateEmployee(@RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest) {
        return new ResponseEntity<>(TResponse.<EmployeeResponse>tResponseBuilder()
                .response(this.employeeService.update(updateEmployeeRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<EmployeeResponse>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<EmployeeResponse>>tResponseBuilder()
                .response(this.employeeService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/count/{isDeleted}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(this.employeeService.getCountByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<EmployeeResponse>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<EmployeeResponse>tResponseBuilder()
                .response(this.employeeService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<TResponse<EmployeeResponse>> getByPhoneNumber(@PathVariable String phoneNumber) {
        return new ResponseEntity<>(TResponse.<EmployeeResponse>tResponseBuilder()
                .response(this.employeeService.getByPhoneNumber(phoneNumber))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = {"startSalary", "endSalary"})
    public ResponseEntity<TResponse<List<EmployeeResponse>>> getAllBySalaryBetween(
            @RequestParam(name = "startSalary", required = false) Double startSalary,
            @RequestParam(name = "endSalary", required = false) Double endSalary) {
        return new ResponseEntity<>(TResponse.<List<EmployeeResponse>>tResponseBuilder()
                .response(this.employeeService.getAllBySalaryBetween(startSalary, endSalary))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<EmployeeResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<EmployeeResponse>>tResponseBuilder()
                .response(this.employeeService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        this.employeeService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

