package src.controller.user.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.user.admin.requests.CreateAdminRequest;
import src.controller.user.admin.requests.UpdateAdminRequest;
import src.controller.user.admin.responses.AdminResponse;
import src.service.user.admin.AdminService;

import java.util.List;

@RestController
@RequestMapping("api/v1/admins")
@RequiredArgsConstructor
@Validated

public class AdminsController {
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<Void> createAdmin(@Valid @RequestBody CreateAdminRequest createAdminRequest) {
        adminService.create(createAdminRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<AdminResponse>> updateAdmin(@RequestBody @Valid UpdateAdminRequest updateAdminRequest) {
        return new ResponseEntity<>(TResponse.<AdminResponse>tResponseBuilder()
                .response(adminService.update(updateAdminRequest)).build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<AdminResponse>>> getAll() {
        return new ResponseEntity<>(
                TResponse.<List<AdminResponse>>tResponseBuilder().response(adminService.getAll()).build()
                , HttpStatus.OK
        );
    }

    @GetMapping("/count/{isDeleted}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(this.adminService.getCountByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<AdminResponse>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<AdminResponse>tResponseBuilder()
                .response(adminService.getById(id)).build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<AdminResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(
                TResponse.<List<AdminResponse>>tResponseBuilder().response(
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

