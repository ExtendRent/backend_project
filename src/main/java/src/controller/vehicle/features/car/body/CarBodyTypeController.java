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
import src.core.rest.BaseController;
import src.service.vehicle.features.car.body.CarBodyTypeService;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("api/v1/carBodyTypes")
@RequiredArgsConstructor
public class CarBodyTypeController extends BaseController {
    private final CarBodyTypeService carBodyTypeService;

    @PostMapping
    public ResponseEntity<Void> createCarBodyType(@RequestBody @Valid CreateCarBodyTypeRequest createCarBodyTypeRequest) {
        log.info(LogConstant.CREATING_NEW_CAR_BODY_TYPE, createCarBodyTypeRequest.toString());
        carBodyTypeService.create(createCarBodyTypeRequest);
        log.info(LogConstant.CAR_BODY_TYPE_SUCCESSFULLY_CREATED);
        return answer(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarBodyTypeResponse>> updateCarBodyType(@RequestBody @Valid UpdateCarBodyTypeRequest updateCarBodyTypeRequest) {
        log.info(LogConstant.UPDATING_CAR_BODY_TYPE, updateCarBodyTypeRequest.toString());
        CarBodyTypeResponse response = carBodyTypeService.update(updateCarBodyTypeRequest);
        log.info(LogConstant.CAR_BODY_TYPE_UPDATED, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CarBodyTypeResponse>>> getAll() {
        log.info(LogConstant.RETRIEVING_ALL_CAR_BODY_TYPES);
        List<CarBodyTypeResponse> response = carBodyTypeService.getAll();
        log.info(LogConstant.RETRIEVED_ALL_CAR_BODY_TYPES, response.size());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CarBodyTypeResponse>> getById(@PathVariable int id) {
        log.info(LogConstant.GETTING_CAR_BODY_TYPE_DETAILS, id);
        CarBodyTypeResponse response = carBodyTypeService.getById(id);
        log.info(LogConstant.RETRIEVED_CAR_BODY_TYPE_DETAILS, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarBodyTypeResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        log.info(LogConstant.RETRIEVING_CAR_BODY_TYPES_BY_DELETED_STATE, isDeleted);
        List<CarBodyTypeResponse> response = carBodyTypeService.getAllByDeletedState(isDeleted);
        log.info(LogConstant.RETRIEVED_CAR_BODY_TYPES_BY_DELETED_STATE, response.size());
        return answer(response, HttpStatus.OK);
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        log.info(LogConstant.DELETING_CAR_BODY_TYPE_WITH_ID, id, isHardDelete);
        carBodyTypeService.delete(id, isHardDelete);
        log.info(LogConstant.CAR_BODY_TYPE_DELETED_SUCCESSFULLY_WITH_ID, id);
        return answer(HttpStatus.NO_CONTENT);
    }
}
