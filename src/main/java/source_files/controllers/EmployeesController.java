package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.userDTOs.EmployeeDTO;
import source_files.data.requests.userRequests.AddEmployeeRequest;
import source_files.data.requests.userRequests.UpdateEmployeeRequest;
import source_files.data.responses.TResponse;
import source_files.services.userServices.abstracts.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/employees")
@AllArgsConstructor
@Validated
@CrossOrigin
public class EmployeesController {
    private final EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<HttpStatus> addEmployee(@RequestBody @Valid AddEmployeeRequest addEmployeeRequest) {
        this.employeeService.add(addEmployeeRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<EmployeeDTO>> updateEmployee(@RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest) {
        return ResponseEntity.ok(TResponse.<EmployeeDTO>tResponseBuilder()
                .response(this.employeeService.update(updateEmployeeRequest))
                .message("Çalışan güncellendi.")
                .build()
        );
    }

    @GetMapping("/")
    public ResponseEntity<TResponse<List<EmployeeDTO>>> getAll() {
        return ResponseEntity.ok(TResponse.<List<EmployeeDTO>>tResponseBuilder()
                .response(this.employeeService.getAll())
                .message("Çalışan listesi getirildi.")
                .build()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<TResponse<EmployeeDTO>> getById(@PathVariable int id) {
        return ResponseEntity.ok(TResponse.<EmployeeDTO>tResponseBuilder()
                .response(this.employeeService.getById(id))
                .message("Çalışan getirildi.")
                .build()
        );
    }

    @GetMapping("{phoneNumber}")
    public ResponseEntity<TResponse<?>> getByPhoneNumber(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.employeeService.getByPhoneNumber(phoneNumber))
                .message("Telefon Numarasına Göre Getirildi")
                .build()
        );
    }

    @GetMapping(params = {"startSalary", "endSalary"})
    public ResponseEntity<TResponse<List<EmployeeDTO>>> getAllBySalaryBetween(
            @RequestParam(name = "startSalary") Double startSalary, @RequestParam(name = "endSalary") Double endSalary) {
        return ResponseEntity.ok(TResponse.<List<EmployeeDTO>>tResponseBuilder()
                .response(this.employeeService.getAllBySalaryBetween(startSalary, endSalary))
                .message(startSalary + "TL ve " + endSalary + "TL Arasındaki Aylık Ücrete Göre Getirildi.")
                .build()
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<EmployeeDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return ResponseEntity.ok(TResponse.<List<EmployeeDTO>>tResponseBuilder()
                .response(this.employeeService.getAllByDeletedState(isDeleted))
                .message("Silinmeyen Araba Listesi döndü.")
                .build()
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<HttpStatus> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        this.employeeService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

