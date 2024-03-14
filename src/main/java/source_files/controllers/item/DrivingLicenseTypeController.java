package source_files.controllers.item;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.controllers.item.dtos.DrivingLicenseTypeDTO;
import source_files.controllers.item.requests.CreateDrivingLicenseTypeRequest;
import source_files.controllers.item.requests.UpdateDrivingLicenseTypeRequest;
import source_files.data.responses.TResponse;
import source_files.services.DrivingLicenseTypeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/drivingLicenseType")
@AllArgsConstructor
@Validated
@CrossOrigin
public class DrivingLicenseTypeController {

    private DrivingLicenseTypeService drivingLicenseService;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody CreateDrivingLicenseTypeRequest request) {
        this.drivingLicenseService.create(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<DrivingLicenseTypeDTO>> update(
            @Valid @RequestBody UpdateDrivingLicenseTypeRequest request) {
        return new ResponseEntity<>(TResponse.<DrivingLicenseTypeDTO>tResponseBuilder()
                .response(this.drivingLicenseService.update(request))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<DrivingLicenseTypeDTO>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<DrivingLicenseTypeDTO>tResponseBuilder()
                .response(this.drivingLicenseService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<DrivingLicenseTypeDTO>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<DrivingLicenseTypeDTO>>tResponseBuilder()
                .response(this.drivingLicenseService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<DrivingLicenseTypeDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {

        return new ResponseEntity<>(TResponse.<List<DrivingLicenseTypeDTO>>tResponseBuilder()
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
