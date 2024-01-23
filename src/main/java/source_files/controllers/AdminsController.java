package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.userDTOs.AdminDTO;
import source_files.data.requests.userRequests.CreateAdminRequest;
import source_files.data.requests.userRequests.UpdateAdminRequest;
import source_files.data.responses.TResponse;
import source_files.services.userServices.abstracts.AdminService;

import java.util.List;

@RestController
@RequestMapping("api/v1/admins")
@AllArgsConstructor
@Validated
@CrossOrigin
public class AdminsController {
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<Void> createAdmin(@Valid @RequestBody CreateAdminRequest createAdminRequest) {
        adminService.create(createAdminRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<AdminDTO>> updateAdmin(@RequestBody @Valid UpdateAdminRequest updateAdminRequest) {
        return new ResponseEntity<>(TResponse.<AdminDTO>tResponseBuilder()
                .response(adminService.update(updateAdminRequest)).build(), HttpStatus.OK
        );
    }

    @GetMapping("/")
    public ResponseEntity<TResponse<List<AdminDTO>>> getAll() {
        return new ResponseEntity<>(
                TResponse.<List<AdminDTO>>tResponseBuilder().response(adminService.getAll()).build()
                , HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<AdminDTO>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<AdminDTO>tResponseBuilder()
                .response(adminService.getById(id)).build(), HttpStatus.OK
        );
    }

    @GetMapping("/{phoneNumber}")
    public ResponseEntity<TResponse<AdminDTO>> getByPhoneNumber(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(TResponse.<AdminDTO>tResponseBuilder()
                .response(adminService.getByPhoneNumber(phoneNumber))
                .build()
        );
    }

    @GetMapping("/{salary}")
    public ResponseEntity<TResponse<List<AdminDTO>>> getAllBySalaryGreaterThanEqual(@PathVariable Double salary) {
        return new ResponseEntity<>(
                TResponse.<List<AdminDTO>>tResponseBuilder().response(
                        adminService.getAllBySalaryGreaterThanEqual(salary)
                ).build(), HttpStatus.OK
        );
    }


    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<AdminDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(
                TResponse.<List<AdminDTO>>tResponseBuilder().response(
                        adminService.getAllByDeletedState(isDeleted)
                ).build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        this.adminService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

