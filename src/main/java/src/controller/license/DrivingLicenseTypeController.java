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
import src.service.license.DrivingLicenseTypeService;

import java.util.List;

import static src.controller.license.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/drivingLicenseType")
@RequiredArgsConstructor
public class DrivingLicenseTypeController {
    private final DrivingLicenseTypeService drivingLicenseService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateDrivingLicenseTypeRequest request) {
        log.info(CREATING_NEW_DRIVING_LICENSE_TYPE, request.toString());
        this.drivingLicenseService.create(request);
        log.info(DRIVING_LICENSE_TYPE_CREATED_SUCCESSFULLY);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<DrivingLicenseTypeResponse>> update(
            @Valid @RequestBody UpdateDrivingLicenseTypeRequest request) {
        log.info(UPDATING_DRIVING_LICENSE_TYPE, request.toString());
        DrivingLicenseTypeResponse updatedDrivingLicenseType = this.drivingLicenseService.update(request);
        log.info(DRIVING_LICENSE_TYPE_UPDATED, updatedDrivingLicenseType.toString());
        return new ResponseEntity<>(TResponse.<DrivingLicenseTypeResponse>tResponseBuilder()
                .response(updatedDrivingLicenseType)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<DrivingLicenseTypeResponse>> getById(@PathVariable int id) {
        log.info(GETTING_DRIVING_LICENSE_TYPE_DETAILS, id);
        DrivingLicenseTypeResponse drivingLicenseType = this.drivingLicenseService.getById(id);
        log.info(DRIVING_LICENSE_TYPE_DETAILS_RETRIEVED, drivingLicenseType.toString());
        return new ResponseEntity<>(TResponse.<DrivingLicenseTypeResponse>tResponseBuilder()
                .response(drivingLicenseType)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<DrivingLicenseTypeResponse>>> getAll() {
        log.info(RETRIEVING_ALL_DRIVING_LICENSE_TYPES);
        List<DrivingLicenseTypeResponse> drivingLicenseTypes = this.drivingLicenseService.getAll();
        log.info(ALL_DRIVING_LICENSE_TYPES_RETRIEVED, drivingLicenseTypes.size());
        return new ResponseEntity<>(TResponse.<List<DrivingLicenseTypeResponse>>tResponseBuilder()
                .response(drivingLicenseTypes)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<DrivingLicenseTypeResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {

        log.info(RETRIEVING_DRIVING_LICENSE_TYPES_BY_DELETED_STATE, isDeleted);
        List<DrivingLicenseTypeResponse> drivingLicenseTypes = this.drivingLicenseService.getAllByDeletedState(isDeleted);
        log.info(DRIVING_LICENSE_TYPES_BY_DELETED_STATE_RETRIEVED, drivingLicenseTypes.size());
        return new ResponseEntity<>(TResponse.<List<DrivingLicenseTypeResponse>>tResponseBuilder()
                .response(drivingLicenseTypes)
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        log.info(DELETING_DRIVING_LICENSE_TYPE_WITH_ID, id, isHardDelete);
        this.drivingLicenseService.delete(id, isHardDelete);
        log.info(DRIVING_LICENSE_TYPE_DELETED_SUCCESSFULLY_WITH_ID, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
