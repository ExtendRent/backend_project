package src.controllers.vehicle.common_features;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controllers.vehicle.requests.vehicleFeatures.shiftType.CreateShiftTypeRequest;
import src.controllers.vehicle.requests.vehicleFeatures.shiftType.UpdateShiftTypeRequest;
import src.controllers.vehicle.responses.ShiftTypeResponse;
import src.data.global_responses.TResponse;
import src.services.vehicle_features.common_features.shift_type.ShiftTypeService;

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
    public ResponseEntity<TResponse<ShiftTypeResponse>> updateFuel(@RequestBody @Valid UpdateShiftTypeRequest updateShiftTypeRequest) {
        return new ResponseEntity<>(TResponse.<ShiftTypeResponse>tResponseBuilder()
                .response(this.shiftTypeService.update(updateShiftTypeRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<ShiftTypeResponse>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<ShiftTypeResponse>tResponseBuilder()
                .response(this.shiftTypeService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("")
    public ResponseEntity<TResponse<List<ShiftTypeResponse>>> getAll() throws Exception {
        return new ResponseEntity<>(TResponse.<List<ShiftTypeResponse>>tResponseBuilder()
                .response(this.shiftTypeService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<ShiftTypeResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<ShiftTypeResponse>>tResponseBuilder()
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
