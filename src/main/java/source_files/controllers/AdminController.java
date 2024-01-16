package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.userDTOs.AdminDTO;
import source_files.data.requests.userRequests.AddAdminRequest;
import source_files.data.requests.userRequests.UpdateAdminRequest;
import source_files.data.responses.TResponse;
import source_files.services.userServices.abstracts.AdminService;

import java.util.List;

@RestController
@RequestMapping("api/admin")
@AllArgsConstructor
public class AdminController {
    private final AdminService adminService;


    @PostMapping
    public ResponseEntity<HttpStatus> addAdmin(@Valid @RequestBody AddAdminRequest addAdminRequest) {
        this.adminService.add(addAdminRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<AdminDTO>> updateAdmin(@RequestBody @Valid UpdateAdminRequest updateAdminRequest) {
        return ResponseEntity.ok(TResponse.<AdminDTO>tResponseBuilder()
                .response(this.adminService.update(updateAdminRequest))
                .message("Admin güncellendi.")
                .build()
        );
    }

    @GetMapping("/")
    public ResponseEntity<TResponse<List<AdminDTO>>> getAll() {
        return ResponseEntity.ok(TResponse.<List<AdminDTO>>tResponseBuilder()
                .response(this.adminService.getAll())
                .message("Admin listesi getirildi.")
                .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<AdminDTO>> getById(@PathVariable int id) {
        return ResponseEntity.ok(TResponse.<AdminDTO>tResponseBuilder()
                .response(this.adminService.getById(id))
                .message("Admin getirildi.")
                .build()
        );
    }

    @GetMapping("{phoneNumber}")
    public ResponseEntity<TResponse<AdminDTO>> getByPhoneNumber(@PathVariable String phoneNumber) {
        return ResponseEntity.ok(TResponse.<AdminDTO>tResponseBuilder()
                .response(this.adminService.getByPhoneNumber(phoneNumber))
                .message("Telefon Numarasına Göre Getirildi")
                .build()
        );
    }

    @GetMapping("{salary}")
    public ResponseEntity<TResponse<List<AdminDTO>>> getAllBySalaryGreaterThanEqual(@PathVariable Double salary) {
        return ResponseEntity.ok(TResponse.<List<AdminDTO>>tResponseBuilder()
                .response(this.adminService.getAllBySalaryGreaterThanEqual(salary))
                .message("Minimum " + salary + "TL Aylık Ücrete Göre Getirildi.")
                .build()
        );
    }


    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<AdminDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return ResponseEntity.ok(TResponse.<List<AdminDTO>>tResponseBuilder()
                .response(this.adminService.getAllByDeletedState(isDeleted))
                .message("Admin listesi döndü.")
                .build()
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<HttpStatus> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        this.adminService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

