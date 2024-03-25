package src.controller.user.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.user.admin.request.CreateAdminRequest;
import src.controller.user.admin.request.UpdateAdminRequest;
import src.controller.user.admin.response.AdminResponse;
import src.core.rest.BaseController;
import src.service.user.admin.AdminService;

import java.util.List;

import static src.controller.user.admin.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/admins")
@RequiredArgsConstructor
public class AdminController extends BaseController {
    private final AdminService adminService;

    @PostMapping
    public ResponseEntity<Void> createAdmin(@Valid @RequestBody CreateAdminRequest createAdminRequest) {
        log.info(CREATING_NEW_ADMIN, createAdminRequest.toString());
        adminService.create(createAdminRequest);
        log.info(ADMIN_SUCCESSFULLY_CREATED);
        return answer(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<AdminResponse>> updateAdmin(@RequestBody @Valid UpdateAdminRequest updateAdminRequest) {
        log.info(UPDATING_ADMIN, updateAdminRequest.toString());
        AdminResponse response = adminService.update(updateAdminRequest);
        log.info(ADMIN_UPDATED, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<AdminResponse>>> getAll() {
        log.info(GETTING_ALL_ADMINS);
        List<AdminResponse> response = adminService.getAll();
        log.info(RETRIEVED_ALL_ADMINS, response.size());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/count/{isDeleted}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable boolean isDeleted) {
        log.info(GETTING_ADMIN_COUNT_BY_DELETED_STATE, isDeleted);
        int response = adminService.getCountByDeletedState(isDeleted);
        log.info(RETRIEVED_ADMIN_COUNT_BY_DELETED_STATE, response);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<AdminResponse>> getById(@PathVariable int id) {
        log.info(GETTING_ADMIN_BY_ID, id);
        AdminResponse response = adminService.getById(id);
        log.info(RETRIEVED_ADMIN_BY_ID, id);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<AdminResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        log.info(GETTING_ADMINS_BY_DELETED_STATE, isDeleted);
        List<AdminResponse> response = adminService.getAllByDeletedState(isDeleted);
        log.info(RETRIEVED_ADMINS_BY_DELETED_STATE, response.size());
        return answer(response, HttpStatus.OK);
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        log.info(DELETING_ADMIN_WITH_ID, id, isHardDelete);
        adminService.delete(id, isHardDelete);
        log.info(ADMIN_DELETED_SUCCESSFULLY_WITH_ID, id);
        return answer(HttpStatus.NO_CONTENT);
    }
}
