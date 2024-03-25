package src.controller.license;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.license.request.CreateDrivingLicenseTypeRequest;
import src.controller.license.request.UpdateDrivingLicenseTypeRequest;
import src.controller.license.response.DrivingLicenseTypeResponse;
import src.core.rest.BaseController;
import src.service.license.DrivingLicenseTypeService;

import java.util.List;

import static src.controller.license.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/drivingLicenseType")
@RequiredArgsConstructor
public class DrivingLicenseTypeController extends BaseController {
    private final DrivingLicenseTypeService drivingLicenseService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateDrivingLicenseTypeRequest request) {
        log.info(CREATING_NEW_DRIVING_LICENSE_TYPE, request.toString());
        drivingLicenseService.create(request);
        log.info(DRIVING_LICENSE_TYPE_CREATED_SUCCESSFULLY);
        return answer(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<DrivingLicenseTypeResponse>> update(
            @Valid @RequestBody UpdateDrivingLicenseTypeRequest request) {
        log.info(UPDATING_DRIVING_LICENSE_TYPE, request.toString());
        DrivingLicenseTypeResponse response = drivingLicenseService.update(request);
        log.info(DRIVING_LICENSE_TYPE_UPDATED, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<DrivingLicenseTypeResponse>> getById(@PathVariable int id) {
        log.info(GETTING_DRIVING_LICENSE_TYPE_DETAILS, id);
        DrivingLicenseTypeResponse response = drivingLicenseService.getById(id);
        log.info(DRIVING_LICENSE_TYPE_DETAILS_RETRIEVED, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<DrivingLicenseTypeResponse>>> getAll() {
        log.info(RETRIEVING_ALL_DRIVING_LICENSE_TYPES);
        List<DrivingLicenseTypeResponse> response = drivingLicenseService.getAll();
        log.info(ALL_DRIVING_LICENSE_TYPES_RETRIEVED, response.size());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<DrivingLicenseTypeResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {

        log.info(RETRIEVING_DRIVING_LICENSE_TYPES_BY_DELETED_STATE, isDeleted);
        List<DrivingLicenseTypeResponse> response = drivingLicenseService.getAllByDeletedState(isDeleted);
        log.info(DRIVING_LICENSE_TYPES_BY_DELETED_STATE_RETRIEVED, response.size());
        return answer(response, HttpStatus.OK);
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        log.info(DELETING_DRIVING_LICENSE_TYPE_WITH_ID, id, isHardDelete);
        drivingLicenseService.delete(id, isHardDelete);
        log.info(DRIVING_LICENSE_TYPE_DELETED_SUCCESSFULLY_WITH_ID, id);
        return answer(HttpStatus.NO_CONTENT);
    }
}
