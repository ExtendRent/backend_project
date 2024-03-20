package src.controller.vehicle.features.common.fuel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.features.common.fuel.request.CreateFuelTypeRequest;
import src.controller.vehicle.features.common.fuel.request.UpdateFuelTypeRequest;
import src.controller.vehicle.features.common.fuel.response.FuelTypeResponse;
import src.service.vehicle.features.common.fuel.FuelTypeService;

import java.util.List;

import static src.controller.vehicle.features.common.fuel.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/fuels")
@RequiredArgsConstructor
public class FuelController {
    private final FuelTypeService fuelService;

    @PostMapping
    public ResponseEntity<Void> createFuel(@RequestBody @Valid CreateFuelTypeRequest createFuelTypeRequest) {
        log.info(CREATING_NEW_FUEL_TYPE, createFuelTypeRequest.toString());
        this.fuelService.create(createFuelTypeRequest);
        log.info(FUEL_TYPE_SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<FuelTypeResponse>> updateFuel(@RequestBody @Valid UpdateFuelTypeRequest updateFuelTypeRequest) {
        log.info(UPDATING_FUEL_TYPE, updateFuelTypeRequest.toString());
        FuelTypeResponse updatedFuel = this.fuelService.update(updateFuelTypeRequest);
        log.info(FUEL_TYPE_UPDATED, updatedFuel.toString());
        return new ResponseEntity<>(TResponse.<FuelTypeResponse>tResponseBuilder()
                .response(updatedFuel)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<FuelTypeResponse>> getById(@PathVariable int id) {
        log.info(GETTING_FUEL_TYPE_DETAILS, id);
        FuelTypeResponse fuelType = this.fuelService.getById(id);
        log.info(RETRIEVED_FUEL_TYPE_DETAILS, fuelType.toString());
        return new ResponseEntity<>(TResponse.<FuelTypeResponse>tResponseBuilder()
                .response(fuelType)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("")
    public ResponseEntity<TResponse<List<FuelTypeResponse>>> getAll() throws Exception {
        log.info(RETRIEVING_ALL_FUEL_TYPES);
        List<FuelTypeResponse> fuelTypes = this.fuelService.getAll();
        log.info(RETRIEVED_ALL_FUEL_TYPES, fuelTypes.size());
        return new ResponseEntity<>(TResponse.<List<FuelTypeResponse>>tResponseBuilder()
                .response(fuelTypes)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<FuelTypeResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        log.info(RETRIEVING_FUEL_TYPES_BY_DELETED_STATE, isDeleted);
        List<FuelTypeResponse> fuelTypes = this.fuelService.getAllByDeletedState(isDeleted);
        log.info(RETRIEVED_FUEL_TYPES_BY_DELETED_STATE, fuelTypes.size());
        return new ResponseEntity<>(TResponse.<List<FuelTypeResponse>>tResponseBuilder()
                .response(fuelTypes)
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        log.info(DELETING_FUEL_TYPE_WITH_ID, id + " , hard delete: " + isHardDelete);
        this.fuelService.delete(id, isHardDelete);
        log.info(FUEL_TYPE_DELETED_SUCCESSFULLY_WITH_ID, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
