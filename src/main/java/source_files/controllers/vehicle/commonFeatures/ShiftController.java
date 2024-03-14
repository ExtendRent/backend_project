package source_files.controllers.vehicle.commonFeatures;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.controllers.vehicle.dtos.ShiftTypeDTO;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.ShiftTypeRequests.CreateShiftTypeRequest;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.ShiftTypeRequests.UpdateShiftTypeRequest;
import source_files.data.responses.TResponse;
import source_files.services.vehicleFeatures.abstracts.ShiftTypeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/gearshifts")
@AllArgsConstructor
@Validated
@CrossOrigin
public class ShiftController {

    private final ShiftTypeService shiftTypeService;

    @PostMapping
    public ResponseEntity<Void> createFuel(@RequestBody @Valid CreateShiftTypeRequest createShiftTypeRequest) {
        this.shiftTypeService.create(createShiftTypeRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<ShiftTypeDTO>> updateFuel(@RequestBody @Valid UpdateShiftTypeRequest updateShiftTypeRequest) {
        return new ResponseEntity<>(TResponse.<ShiftTypeDTO>tResponseBuilder()
                .response(this.shiftTypeService.update(updateShiftTypeRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<ShiftTypeDTO>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<ShiftTypeDTO>tResponseBuilder()
                .response(this.shiftTypeService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("")
    public ResponseEntity<TResponse<List<ShiftTypeDTO>>> getAll() throws Exception {
        return new ResponseEntity<>(TResponse.<List<ShiftTypeDTO>>tResponseBuilder()
                .response(this.shiftTypeService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<ShiftTypeDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<ShiftTypeDTO>>tResponseBuilder()
                .response(this.shiftTypeService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        this.shiftTypeService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
