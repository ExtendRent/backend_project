package src.controller.vehicle.features.car.body;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.features.car.body.request.CreateCarBodyTypeRequest;
import src.controller.vehicle.features.car.body.request.UpdateCarBodyTypeRequest;
import src.controller.vehicle.features.car.body.response.CarBodyTypeResponse;
import src.service.vehicle.features.car.body.CarBodyTypeService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/carBodyTypes")
@RequiredArgsConstructor
public class CarBodyTypeController {
    private final CarBodyTypeService carBodyTypeService;

    @PostMapping
    public ResponseEntity<Void> createCarBodyType(@RequestBody @Valid CreateCarBodyTypeRequest createCarBodyTypeRequest) {
        log.info(LogConstant.CREATING_NEW_CAR_BODY_TYPE, createCarBodyTypeRequest.toString());
        this.carBodyTypeService.create(createCarBodyTypeRequest);
        log.info(LogConstant.CAR_BODY_TYPE_SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarBodyTypeResponse>> updateCarBodyType(@RequestBody @Valid UpdateCarBodyTypeRequest updateCarBodyTypeRequest) {
        log.info(LogConstant.UPDATING_CAR_BODY_TYPE, updateCarBodyTypeRequest.toString());
        CarBodyTypeResponse updatedCarBodyType = this.carBodyTypeService.update(updateCarBodyTypeRequest);
        log.info(LogConstant.CAR_BODY_TYPE_UPDATED, updatedCarBodyType.toString());
        return new ResponseEntity<>(TResponse.<CarBodyTypeResponse>tResponseBuilder()
                .response(updatedCarBodyType)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CarBodyTypeResponse>>> getAll() {
        log.info(LogConstant.RETRIEVING_ALL_CAR_BODY_TYPES);
        List<CarBodyTypeResponse> carBodyTypes = this.carBodyTypeService.getAll();
        log.info(LogConstant.RETRIEVED_ALL_CAR_BODY_TYPES, carBodyTypes.size());
        return new ResponseEntity<>(TResponse.<List<CarBodyTypeResponse>>tResponseBuilder()
                .response(carBodyTypes)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CarBodyTypeResponse>> getById(@PathVariable int id) {
        log.info(LogConstant.GETTING_CAR_BODY_TYPE_DETAILS, id);
        CarBodyTypeResponse carBodyType = this.carBodyTypeService.getById(id);
        log.info(LogConstant.RETRIEVED_CAR_BODY_TYPE_DETAILS, carBodyType.toString());
        return new ResponseEntity<>(TResponse.<CarBodyTypeResponse>tResponseBuilder()
                .response(carBodyType)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarBodyTypeResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        log.info(LogConstant.RETRIEVING_CAR_BODY_TYPES_BY_DELETED_STATE, isDeleted);
        List<CarBodyTypeResponse> carBodyTypes = this.carBodyTypeService.getAllByDeletedState(isDeleted);
        log.info(LogConstant.RETRIEVED_CAR_BODY_TYPES_BY_DELETED_STATE, carBodyTypes.size());
        return new ResponseEntity<>(TResponse.<List<CarBodyTypeResponse>>tResponseBuilder()
                .response(carBodyTypes)
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        log.info(LogConstant.DELETING_CAR_BODY_TYPE_WITH_ID, id, isHardDelete);
        this.carBodyTypeService.delete(id, isHardDelete);
        log.info(LogConstant.CAR_BODY_TYPE_DELETED_SUCCESSFULLY_WITH_ID, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
