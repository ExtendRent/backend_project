package src.controller.license;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.license.requests.CreateDrivingLicenseTypeRequest;
import src.controller.license.requests.UpdateDrivingLicenseTypeRequest;
import src.controller.license.responses.DrivingLicenseTypeResponse;
import src.service.license.DrivingLicenseTypeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/drivingLicenseType")
@RequiredArgsConstructor
public class DrivingLicenseTypeController {
    private final DrivingLicenseTypeService drivingLicenseService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateDrivingLicenseTypeRequest request) {
        this.drivingLicenseService.create(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<DrivingLicenseTypeResponse>> update(
            @Valid @RequestBody UpdateDrivingLicenseTypeRequest request) {
        return new ResponseEntity<>(TResponse.<DrivingLicenseTypeResponse>tResponseBuilder()
                .response(this.drivingLicenseService.update(request))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<DrivingLicenseTypeResponse>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<DrivingLicenseTypeResponse>tResponseBuilder()
                .response(this.drivingLicenseService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<DrivingLicenseTypeResponse>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<DrivingLicenseTypeResponse>>tResponseBuilder()
                .response(this.drivingLicenseService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<DrivingLicenseTypeResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {

        return new ResponseEntity<>(TResponse.<List<DrivingLicenseTypeResponse>>tResponseBuilder()
                .response(this.drivingLicenseService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        this.drivingLicenseService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
