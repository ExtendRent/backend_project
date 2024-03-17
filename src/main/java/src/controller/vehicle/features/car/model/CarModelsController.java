package src.controller.vehicle.features.car.model;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.features.car.model.requests.CreateCarModelRequest;
import src.controller.vehicle.features.car.model.requests.UpdateCarModelRequest;
import src.controller.vehicle.features.car.model.responses.CarModelResponse;
import src.service.vehicle.features.car.model.CarModelService;

import java.util.List;

@RestController
@RequestMapping("api/v1/carModels")
@RequiredArgsConstructor
@Validated

public class CarModelsController {

    private final CarModelService carModelService;

    @PostMapping
    public ResponseEntity<Void> createCarModel(@RequestBody CreateCarModelRequest createCarModelRequest) {
        this.carModelService.create(createCarModelRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarModelResponse>> updateCarModel(@RequestBody UpdateCarModelRequest updateCarModelRequest) {
        return new ResponseEntity<>(TResponse.<CarModelResponse>tResponseBuilder()
                .response(carModelService.update(updateCarModelRequest))
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
