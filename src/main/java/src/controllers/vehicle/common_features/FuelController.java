package src.controllers.vehicle.common_features;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controllers.vehicle.requests.vehicleFeatures.fuelType.CreateFuelTypeRequest;
import src.controllers.vehicle.requests.vehicleFeatures.fuelType.UpdateFuelTypeRequest;
import src.controllers.vehicle.responses.FuelTypeResponse;
import src.data.global_responses.TResponse;
import src.services.vehicle_features.common_features.fuel_type.FuelTypeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/fuels")
@AllArgsConstructor
@Validated
@CrossOrigin
public class FuelController {

    private final FuelTypeService fuelService;

    @PostMapping
    public ResponseEntity<Void> createFuel(@RequestBody @Valid CreateFuelTypeRequest createFuelTypeRequest) {
        this.fuelService.create(createFuelTypeRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<FuelTypeResponse>> updateFuel(@RequestBody @Valid UpdateFuelTypeRequest updateFuelTypeRequest) {
        return new ResponseEntity<>(TResponse.<FuelTypeResponse>tResponseBuilder()
                .response(this.fuelService.update(updateFuelTypeRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<FuelTypeResponse>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<FuelTypeResponse>tResponseBuilder()
                .response(this.fuelService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("")
    public ResponseEntity<TResponse<List<FuelTypeResponse>>> getAll() throws Exception {
        return new ResponseEntity<>(TResponse.<List<FuelTypeResponse>>tResponseBuilder()
                .response(this.fuelService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<FuelTypeResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<FuelTypeResponse>>tResponseBuilder()
                .response(this.fuelService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        this.fuelService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
