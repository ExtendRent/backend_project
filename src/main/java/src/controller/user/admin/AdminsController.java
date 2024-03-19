package src.controller.user.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import static src.controller.user.admin.LogConstant.*;

@RestController
@RequestMapping("api/v1/admins")
@RequiredArgsConstructor
@Validated
public class AdminsController {
    private static final Logger logger = LoggerFactory.getLogger(AdminsController.class);
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<Void> createAdmin(@Valid @RequestBody CreateAdminRequest createAdminRequest) {
        logger.info(CREATING_NEW_ADMIN, createAdminRequest.toString());
        adminService.create(createAdminRequest);
        logger.info(ADMIN_SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<AdminResponse>> updateAdmin(@RequestBody @Valid UpdateAdminRequest updateAdminRequest) {
        logger.info(UPDATING_ADMIN, updateAdminRequest.toString());
        AdminResponse updatedAdmin = adminService.update(updateAdminRequest);
        logger.info(ADMIN_UPDATED, updatedAdmin.toString());
        return new ResponseEntity<>(TResponse.<AdminResponse>tResponseBuilder()
                .response(updatedAdmin).build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<AdminResponse>>> getAll() {
        logger.info(GETTING_ALL_ADMINS);
        List<AdminResponse> admins = adminService.getAll();
        logger.info(RETRIEVED_ALL_ADMINS, admins.size());
        return new ResponseEntity<>(
                TResponse.<List<AdminResponse>>tResponseBuilder().response(admins).build()
                , HttpStatus.OK
        );
    }

    @GetMapping("/count/{isDeleted}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable boolean isDeleted) {
        logger.info(GETTING_ADMIN_COUNT_BY_DELETED_STATE, isDeleted);
        int count = adminService.getCountByDeletedState(isDeleted);
        logger.info(RETRIEVED_ADMIN_COUNT_BY_DELETED_STATE, count);
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(count)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<AdminResponse>> getById(@PathVariable int id) {
        logger.info(GETTING_ADMIN_BY_ID, id);
        AdminResponse admin = adminService.getById(id);
        logger.info(RETRIEVED_ADMIN_BY_ID, id);
        return new ResponseEntity<>(TResponse.<AdminResponse>tResponseBuilder()
                .response(admin).build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<AdminResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        logger.info(GETTING_ADMINS_BY_DELETED_STATE, isDeleted);
        List<AdminResponse> admins = adminService.getAllByDeletedState(isDeleted);
        logger.info(RETRIEVED_ADMINS_BY_DELETED_STATE, admins.size());
        return new ResponseEntity<>(
                TResponse.<List<AdminResponse>>tResponseBuilder().response(admins).build()
                , HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        logger.info(DELETING_ADMIN_WITH_ID, id, isHardDelete);
        adminService.delete(id, isHardDelete);
        logger.info(ADMIN_DELETED_SUCCESSFULLY_WITH_ID, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
