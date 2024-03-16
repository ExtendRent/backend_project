package src.controllers.vehicle.cars_features;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controllers.vehicle.requests.vehicleFeatures.carModel.CreateCarModelRequest;
import src.controllers.vehicle.requests.vehicleFeatures.carModel.UpdateCarModelRequest;
import src.controllers.vehicle.responses.CarModelResponse;
import src.data.global_responses.TResponse;
import src.services.vehicle_features.car_features.car_model.CarModelService;

import java.util.List;

@RestController
@RequestMapping("api/v1/carModels")
@AllArgsConstructor
@Validated
@CrossOrigin
public class CarModelsController {

    private CarModelService carModelService;

    @PostMapping
    public ResponseEntity<Void> createCarModel(@RequestBody CreateCarModelRequest createCarModelRequest) {
        this.carModelService.create(createCarModelRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarModelResponse>> updateCarModel(@RequestBody UpdateCarModelRequest updateCarModelRequest) {
        return new ResponseEntity<>(TResponse.<CarModelResponse>tResponseBuilder()
                .response(this.carModelService.update(updateCarModelRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CarModelResponse>> getByCarModelId(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<CarModelResponse>tResponseBuilder()
                .response(this.carModelService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CarModelResponse>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<CarModelResponse>>tResponseBuilder()
                .response(this.carModelService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/brands/{brandId}")
    public ResponseEntity<TResponse<?>> getByBrandId(@PathVariable int brandId) {
        return new ResponseEntity<>(TResponse.tResponseBuilder()
                .response(this.carModelService.getAllByBrandId(brandId))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarModelResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<CarModelResponse>>tResponseBuilder()
                .response(this.carModelService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        this.carModelService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
