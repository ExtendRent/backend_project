package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.userDTOs.EmployeeDTO;
import source_files.data.requests.userRequests.AddEmployeeRequest;
import source_files.data.requests.userRequests.UpdateEmployeeRequest;
import source_files.data.responses.TResponse;
import source_files.services.userServices.abstracts.EmployeeService;

import java.util.List;

@RestController
@RequestMapping("api/employee")
@AllArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping("/add/employee")
    public ResponseEntity<TResponse<?>> addEmployee(@RequestBody @Valid AddEmployeeRequest addEmployeeRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.employeeService.add(addEmployeeRequest))
                .message("Çalışan eklendi.")
                .build()
        );
    }

    @PutMapping("/update/employee")
    public ResponseEntity<TResponse<?>> updateEmployee(@RequestBody @Valid UpdateEmployeeRequest updateEmployeeRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.employeeService.update(updateEmployeeRequest))
                .message("Çalışan güncellendi.")
                .build()
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<TResponse<?>> getAll() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.employeeService.getAll())
                .message("Çalışan listesi getirildi.")
                .build()
        );
    }

    @GetMapping("/getById")
    public ResponseEntity<TResponse<?>> getById(@RequestParam int id) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.employeeService.getById(id))
                .message("Çalışan getirildi.")
                .build()
        );
    }

    @GetMapping("/getPhoneNumber")
    public ResponseEntity<TResponse<?>> getByPhoneNumber(@RequestParam String phoneNumber) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.employeeService.getByPhoneNumber(phoneNumber))
                .message("Telefon Numarasına Göre Getirildi")
                .build()
        );
    }

    @GetMapping("/getAllBySalaryBetween")
    public ResponseEntity<TResponse<?>> getAllBySalaryBetween(@RequestParam Double salary1, Double salary2) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.employeeService.findAllBySalaryBetween(salary1, salary2))
                .message(salary1 + "TL ve " + salary2 + "TL Arasındaki Aylık Ücrete Göre Getirildi.")
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TResponse<?>> delete(@PathVariable int id, boolean isHardDelete) {

        this.employeeService.delete(id, isHardDelete);
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .message("Çalışan silindi.")
                .build()
        );
    }

}

