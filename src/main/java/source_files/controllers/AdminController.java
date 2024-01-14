package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.requests.userRequests.AddAdminRequest;
import source_files.data.requests.userRequests.UpdateAdminRequest;
import source_files.data.responses.TResponse;
import source_files.services.userServices.abstracts.AdminService;

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


    @GetMapping("/getAllByDeletedState")
    public ResponseEntity<TResponse<?>> getAllByIsDeletedFalse() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.adminService.getAllByIsDeletedFalse())
                .message("Mevcut Admin Listesi Getirildi.")
                .build()
        );
    }

    @GetMapping("/getAllByIsDeletedTrue")
    public ResponseEntity<TResponse<?>> getAllByIsDeletedTrue() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.adminService.getAllByIsDeletedTrue())
                .message("Soft Delete ile Silinen Admin Listesi Getirildi.")
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

