package src.controller.license;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.license.requests.CreateDrivingLicenseTypeRequest;
import src.controller.license.requests.UpdateDrivingLicenseTypeRequest;
import src.controller.license.responses.DrivingLicenseTypeResponse;
import src.service.license.DrivingLicenseTypeService;

import java.util.List;

import static src.controller.license.LogConstant.*;

@RestController
@RequestMapping("api/v1/drivingLicenseType")
@RequiredArgsConstructor
public class DrivingLicenseTypeController {
    private static final Logger logger = LoggerFactory.getLogger(DrivingLicenseTypeController.class);
    private final DrivingLicenseTypeService drivingLicenseService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateDrivingLicenseTypeRequest request) {
        logger.info(CREATING_NEW_DRIVING_LICENSE_TYPE, request.toString());
        this.drivingLicenseService.create(request);
        logger.info(DRIVING_LICENSE_TYPE_CREATED_SUCCESSFULLY);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<DrivingLicenseTypeResponse>> update(
            @Valid @RequestBody UpdateDrivingLicenseTypeRequest request) {
        logger.info(UPDATING_DRIVING_LICENSE_TYPE, request.toString());
        DrivingLicenseTypeResponse updatedDrivingLicenseType = this.drivingLicenseService.update(request);
        logger.info(DRIVING_LICENSE_TYPE_UPDATED, updatedDrivingLicenseType.toString());
        return new ResponseEntity<>(TResponse.<DrivingLicenseTypeResponse>tResponseBuilder()
                .response(updatedDrivingLicenseType)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<DrivingLicenseTypeResponse>> getById(@PathVariable int id) {
        logger.info(GETTING_DRIVING_LICENSE_TYPE_DETAILS, id);
        DrivingLicenseTypeResponse drivingLicenseType = this.drivingLicenseService.getById(id);
        logger.info(DRIVING_LICENSE_TYPE_DETAILS_RETRIEVED, drivingLicenseType.toString());
        return new ResponseEntity<>(TResponse.<DrivingLicenseTypeResponse>tResponseBuilder()
                .response(drivingLicenseType)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<DrivingLicenseTypeResponse>>> getAll() {
        logger.info(RETRIEVING_ALL_DRIVING_LICENSE_TYPES);
        List<DrivingLicenseTypeResponse> drivingLicenseTypes = this.drivingLicenseService.getAll();
        logger.info(ALL_DRIVING_LICENSE_TYPES_RETRIEVED, drivingLicenseTypes.size());
        return new ResponseEntity<>(TResponse.<List<DrivingLicenseTypeResponse>>tResponseBuilder()
                .response(drivingLicenseTypes)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<DrivingLicenseTypeResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {

        logger.info(RETRIEVING_DRIVING_LICENSE_TYPES_BY_DELETED_STATE, isDeleted);
        List<DrivingLicenseTypeResponse> drivingLicenseTypes = this.drivingLicenseService.getAllByDeletedState(isDeleted);
        logger.info(DRIVING_LICENSE_TYPES_BY_DELETED_STATE_RETRIEVED, drivingLicenseTypes.size());
        return new ResponseEntity<>(TResponse.<List<DrivingLicenseTypeResponse>>tResponseBuilder()
                .response(drivingLicenseTypes)
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        logger.info(DELETING_DRIVING_LICENSE_TYPE_WITH_ID, id, isHardDelete);
        this.drivingLicenseService.delete(id, isHardDelete);
        logger.info(DRIVING_LICENSE_TYPE_DELETED_SUCCESSFULLY_WITH_ID, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
