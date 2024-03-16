package src.controllers.vehicle.cars_features;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controllers.vehicle.requests.vehicleFeatures.carBodyType.CreateCarBodyTypeRequest;
import src.controllers.vehicle.requests.vehicleFeatures.carBodyType.UpdateCarBodyTypeRequest;
import src.controllers.vehicle.responses.CarBodyTypeResponse;
import src.data.global_responses.TResponse;
import src.services.vehicle_features.car_features.car_body_type.CarBodyTypeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/carBodyTypes")
@AllArgsConstructor
@Validated
@CrossOrigin
public class CarBodyTypesController {

    private final CarBodyTypeService carBodyTypeService;

    @PostMapping
    public ResponseEntity<Void> createCarBodyType(@RequestBody @Valid CreateCarBodyTypeRequest createCarBodyTypeRequest) {
        this.carBodyTypeService.create(createCarBodyTypeRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarBodyTypeResponse>> updateCarBodyType(@RequestBody @Valid UpdateCarBodyTypeRequest updateCarBodyTypeRequest) {
        return new ResponseEntity<>(TResponse.<CarBodyTypeResponse>tResponseBuilder()
                .response(this.carBodyTypeService.update(updateCarBodyTypeRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CarBodyTypeResponse>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<CarBodyTypeResponse>>tResponseBuilder()
                .response(this.carBodyTypeService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CarBodyTypeResponse>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<CarBodyTypeResponse>tResponseBuilder()
                .response(this.carBodyTypeService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarBodyTypeResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<CarBodyTypeResponse>>tResponseBuilder()
                .response(this.carBodyTypeService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        this.carBodyTypeService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
