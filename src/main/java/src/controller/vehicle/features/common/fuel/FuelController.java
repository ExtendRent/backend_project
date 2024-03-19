package src.controller.vehicle.features.common.fuel;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.features.common.color.ColorsController;
import src.controller.vehicle.features.common.fuel.requests.CreateFuelTypeRequest;
import src.controller.vehicle.features.common.fuel.requests.UpdateFuelTypeRequest;
import src.controller.vehicle.features.common.fuel.responses.FuelTypeResponse;
import src.service.vehicle.features.common.fuel.FuelTypeService;

import java.util.List;

import static src.controller.vehicle.features.common.fuel.LogConstant.*;

@RestController
@RequestMapping("api/v1/fuels")
@RequiredArgsConstructor
@Validated
public class FuelController {
    private static final Logger logger = LoggerFactory.getLogger(ColorsController.class);
    private final FuelTypeService fuelService;

    @PostMapping
    public ResponseEntity<Void> createFuel(@RequestBody @Valid CreateFuelTypeRequest createFuelTypeRequest) {
        logger.info(CREATING_NEW_FUEL_TYPE, createFuelTypeRequest.toString());
        this.fuelService.create(createFuelTypeRequest);
        logger.info(FUEL_TYPE_SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<FuelTypeResponse>> updateFuel(@RequestBody @Valid UpdateFuelTypeRequest updateFuelTypeRequest) {
        logger.info(UPDATING_FUEL_TYPE, updateFuelTypeRequest.toString());
        FuelTypeResponse updatedFuel = this.fuelService.update(updateFuelTypeRequest);
        logger.info(FUEL_TYPE_UPDATED, updatedFuel.toString());
        return new ResponseEntity<>(TResponse.<FuelTypeResponse>tResponseBuilder()
                .response(updatedFuel)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<FuelTypeResponse>> getById(@PathVariable int id) {
        logger.info(GETTING_FUEL_TYPE_DETAILS, id);
        FuelTypeResponse fuelType = this.fuelService.getById(id);
        logger.info(RETRIEVED_FUEL_TYPE_DETAILS, fuelType.toString());
        return new ResponseEntity<>(TResponse.<FuelTypeResponse>tResponseBuilder()
                .response(fuelType)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("")
    public ResponseEntity<TResponse<List<FuelTypeResponse>>> getAll() throws Exception {
        logger.info(RETRIEVING_ALL_FUEL_TYPES);
        List<FuelTypeResponse> fuelTypes = this.fuelService.getAll();
        logger.info(RETRIEVED_ALL_FUEL_TYPES, fuelTypes.size());
        return new ResponseEntity<>(TResponse.<List<FuelTypeResponse>>tResponseBuilder()
                .response(fuelTypes)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<FuelTypeResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        logger.info(RETRIEVING_FUEL_TYPES_BY_DELETED_STATE, isDeleted);
        List<FuelTypeResponse> fuelTypes = this.fuelService.getAllByDeletedState(isDeleted);
        logger.info(RETRIEVED_FUEL_TYPES_BY_DELETED_STATE, fuelTypes.size());
        return new ResponseEntity<>(TResponse.<List<FuelTypeResponse>>tResponseBuilder()
                .response(fuelTypes)
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        logger.info(DELETING_FUEL_TYPE_WITH_ID, id + " , hard delete: " + isHardDelete);
        this.fuelService.delete(id, isHardDelete);
        logger.info(FUEL_TYPE_DELETED_SUCCESSFULLY_WITH_ID, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
