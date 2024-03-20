package src.controller.vehicle.features.car.model;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.features.car.model.request.CreateCarModelRequest;
import src.controller.vehicle.features.car.model.request.UpdateCarModelRequest;
import src.controller.vehicle.features.car.model.response.CarModelResponse;
import src.service.vehicle.features.car.model.CarModelService;

import java.util.List;

import static src.controller.vehicle.features.car.model.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/carModels")
@RequiredArgsConstructor
public class CarModelController {
    private final CarModelService carModelService;

    @PostMapping
    public ResponseEntity<Void> createCarModel(@RequestBody CreateCarModelRequest createCarModelRequest) {
        log.info(CREATING_NEW_CAR_MODEL, createCarModelRequest.toString());
        this.carModelService.create(createCarModelRequest);
        log.info(CAR_MODEL_SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarModelResponse>> updateCarModel(@RequestBody UpdateCarModelRequest updateCarModelRequest) {
        log.info(UPDATING_CAR_MODEL, updateCarModelRequest.toString());
        CarModelResponse response = carModelService.update(updateCarModelRequest);
        log.info(CAR_MODEL_UPDATED, response.toString());
        return new ResponseEntity<>(TResponse.<CarModelResponse>tResponseBuilder()
                .response(response)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CarModelResponse>> getByCarModelId(@PathVariable int id) {
        log.info(GETTING_CAR_MODEL_DETAILS, id);
        CarModelResponse response = this.carModelService.getById(id);
        log.info(RETRIEVED_CAR_MODEL_DETAILS, response.toString());
        return new ResponseEntity<>(TResponse.<CarModelResponse>tResponseBuilder()
                .response(response)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CarModelResponse>>> getAll() {
        log.info(RETRIEVING_ALL_CAR_MODELS);
        List<CarModelResponse> response = this.carModelService.getAll();
        log.info(RETRIEVED_ALL_CAR_MODELS, response.size());
        return new ResponseEntity<>(TResponse.<List<CarModelResponse>>tResponseBuilder()
                .response(response)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/brands/{brandId}")
    public ResponseEntity<TResponse<?>> getByBrandId(@PathVariable int brandId) {
        log.info(RETRIEVING_CAR_MODELS_BY_BRAND_ID, brandId);
        return new ResponseEntity<>(TResponse.tResponseBuilder()
                .response(this.carModelService.getAllByBrandId(brandId))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarModelResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        log.info(RETRIEVING_CAR_MODELS_BY_DELETED_STATE_BEFORE_CALL, isDeleted);
        List<CarModelResponse> response = this.carModelService.getAllByDeletedState(isDeleted);
        log.info(RETRIEVING_CAR_MODELS_BY_DELETED_STATE_AFTER_CALL, response.size());
        return new ResponseEntity<>(TResponse.<List<CarModelResponse>>tResponseBuilder()
                .response(response)
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        log.info(DELETING_CAR_MODEL_WITH_ID, id, isHardDelete);
        this.carModelService.delete(id, isHardDelete);
        log.info(CAR_MODEL_DELETED_SUCCESSFULLY_WITH_ID, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
