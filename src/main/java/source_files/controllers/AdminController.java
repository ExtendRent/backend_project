package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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


    @PostMapping("/add/admin")
    public ResponseEntity<TResponse<?>> addAdmin(@RequestBody @Valid AddAdminRequest addAdminRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.adminService.add(addAdminRequest))
                .message("Admin eklendi.")
                .build()
        );
    }

    @PutMapping("/update/admin")
    public ResponseEntity<TResponse<?>> updateAdmin(@RequestBody @Valid UpdateAdminRequest updateAdminRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.adminService.update(updateAdminRequest))
                .message("Admin güncellendi.")
                .build()
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<TResponse<?>> getAll() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.adminService.getAll())
                .message("Admin listesi getirildi.")
                .build()
        );
    }

    @GetMapping("/getById")
    public ResponseEntity<TResponse<?>> getById(@RequestParam int id) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.adminService.getById(id))
                .message("Admin getirildi.")
                .build()
        );
    }

    @GetMapping("/getPhoneNumber")
    public ResponseEntity<TResponse<?>> getByPhoneNumber(@RequestParam String phoneNumber) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.adminService.getByPhoneNumber(phoneNumber))
                .message("Telefon Numarasına Göre Getirildi")
                .build()
        );
    }

    @GetMapping("/getAllBySalaryGreaterThanEqual")
    public ResponseEntity<TResponse<?>> getAllBySalaryGreaterThanEqual(@RequestParam Double salary) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
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

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TResponse<?>> delete(@PathVariable int id, boolean isHardDelete) {

        this.adminService.delete(id, isHardDelete);
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .message("Admin silindi.")
                .build()
        );
    }
}

