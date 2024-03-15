package source_files.controllers.user;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.controllers.user.dtos.EmployeeDTO;
import source_files.controllers.user.requests.employee.CreateEmployeeRequest;
import source_files.controllers.user.requests.employee.UpdateEmployeeRequest;
import source_files.data.responses.TResponse;
import source_files.services.user.abstracts.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
@AllArgsConstructor
@Validated
@CrossOrigin
public class EmployeesController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Void> createEmployee(@RequestBody @Valid CreateEmployeeRequest createEmployeeRequest) {
        this.employeeService.create(createEmployeeRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<EmployeeDTO>> updateEmployee(@RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest) {
        return new ResponseEntity<>(TResponse.<EmployeeDTO>tResponseBuilder()
                .response(this.employeeService.update(updateEmployeeRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<EmployeeDTO>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<EmployeeDTO>>tResponseBuilder()
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
    public ResponseEntity<TResponse<EmployeeDTO>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<EmployeeDTO>tResponseBuilder()
                .response(this.employeeService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<TResponse<EmployeeDTO>> getByPhoneNumber(@PathVariable String phoneNumber) {
        return new ResponseEntity<>(TResponse.<EmployeeDTO>tResponseBuilder()
                .response(this.employeeService.getByPhoneNumber(phoneNumber))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = {"startSalary", "endSalary"})
    public ResponseEntity<TResponse<List<EmployeeDTO>>> getAllBySalaryBetween(
            @RequestParam(name = "startSalary", required = false) Double startSalary,
            @RequestParam(name = "endSalary", required = false) Double endSalary) {
        return new ResponseEntity<>(TResponse.<List<EmployeeDTO>>tResponseBuilder()
                .response(this.employeeService.getAllBySalaryBetween(startSalary, endSalary))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<EmployeeDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<EmployeeDTO>>tResponseBuilder()
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

