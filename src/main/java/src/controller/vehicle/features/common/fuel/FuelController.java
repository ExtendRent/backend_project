package src.controller.vehicle.features.common.fuel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.features.common.fuel.requests.CreateFuelTypeRequest;
import src.controller.vehicle.features.common.fuel.requests.UpdateFuelTypeRequest;
import src.controller.vehicle.features.common.fuel.responses.FuelTypeResponse;
import src.service.vehicle.features.common.fuel.FuelTypeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/fuels")
@RequiredArgsConstructor
@Validated

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
