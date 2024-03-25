package src.controller.vehicle.car;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.car.request.CreateCarRequest;
import src.controller.vehicle.car.request.UpdateCarRequest;
import src.controller.vehicle.car.response.CarResponse;
import src.core.rest.BaseController;
import src.service.vehicle.car.CarService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static src.controller.vehicle.car.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/cars")
@RequiredArgsConstructor
public class CarController extends BaseController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<Void> createCar(@RequestBody @Valid CreateCarRequest createCarRequest) throws IOException {
        log.info(CREATING_NEW_CAR, createCarRequest);
        carService.create(createCarRequest);
        log.info(CAR_SUCCESSFULLY_CREATED);
        return answer(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarResponse>> updateCar(@Valid @RequestBody UpdateCarRequest updateCarRequest) throws IOException {
        log.info(UPDATING_CAR, updateCarRequest);
        CarResponse response = carService.update(updateCarRequest);
        log.info(CAR_UPDATED, response);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CarResponse>> getById(@PathVariable int id) {
        log.info(GETTING_CAR_DETAILS, id);
        CarResponse response = carService.getById(id);
        log.info(RETRIEVED_CAR_DETAILS, response);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CarResponse>>> getAll() {
        log.info(RETRIEVING_ALL_CARS);
        List<CarResponse> response = carService.getAll();
        log.info(RETRIEVED_ALL_CARS, response.size());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/count/{isDeleted}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable boolean isDeleted) {
        log.info(RETRIEVING_CAR_COUNT_BY_DELETED_STATE, isDeleted);
        int response = carService.getCountByDeletedState(isDeleted);
        log.info(RETRIEVED_CAR_COUNT_BY_DELETED_STATE, response);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/countByStatus/{statusId}")
    public ResponseEntity<TResponse<Integer>> getCountByStatusId(@PathVariable int statusId) {
        log.info(RETRIEVING_CAR_COUNT_BY_STATUS_ID, statusId);
        int response = carService.getCountByStatusId(statusId);
        log.info(RETRIEVED_CAR_COUNT_BY_STATUS_ID, response);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<TResponse<List<CarResponse>>> getAllFiltered(
            @RequestParam(name = "customerId", required = false) Integer customerId,
            @RequestParam(name = "licenseSuitable", required = false) Boolean licenseSuitable,
            @RequestParam(name = "startDate", required = false) LocalDate startDate,
            @RequestParam(name = "endDate", required = false) LocalDate endDate,
            @RequestParam(name = "brandId", required = false) Integer brandId,
            @RequestParam(name = "modelId", required = false) Integer modelId,
            @RequestParam(name = "colorId", required = false) Integer colorId,
            @RequestParam(name = "fuelTypeId", required = false) Integer fuelTypeId,
            @RequestParam(name = "shiftTypeId", required = false) Integer shiftTypeId,
            @RequestParam(name = "seat", required = false) Integer seat,
            @RequestParam(name = "luggage", required = false) Integer luggage,
            @RequestParam(name = "startPrice", required = false) Integer startPrice,
            @RequestParam(name = "endPrice", required = false) Integer endPrice,
            @RequestParam(name = "startYear", required = false) Integer startYear,
            @RequestParam(name = "endYear", required = false) Integer endYear,
            @RequestParam(name = "isDeleted", required = false) Boolean isDeleted,
            @RequestParam(name = "statusId", required = false) Integer statusId,
            @RequestParam(name = "segmentId", required = false) Integer segmentId
    ) {
        log.info(FILTERING_CARS);
        List<CarResponse> response = carService.getAllFiltered(
                customerId, licenseSuitable, startDate,
                endDate, startPrice,
                endPrice, isDeleted,
                statusId, colorId, seat, luggage,
                modelId, startYear, endYear,
                brandId, fuelTypeId,
                shiftTypeId, segmentId);
        log.info(FILTERED_CARS, response.size());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping(params = {"startDate", "endDate"})
    public ResponseEntity<TResponse<List<CarResponse>>> getAllByAvailabilityBetween(
            @RequestParam(name = "startDate", required = false) LocalDate startDate,
            @RequestParam(name = "endDate", required = false) LocalDate endDate) {
        log.info(RETRIEVING_CARS_BY_AVAILABILITY, startDate, endDate);
        List<CarResponse> response = carService.getAllByAvailabilityBetween(startDate, endDate);
        log.info(RETRIEVED_CARS_BY_AVAILABILITY, response.size());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        log.info(RETRIEVING_ALL_CARS_BY_DELETED_STATE, isDeleted);
        List<CarResponse> response = carService.getAllByDeletedState(isDeleted);
        log.info(RETRIEVED_ALL_CARS_BY_DELETED_STATE, response.size());
        return answer(response, HttpStatus.OK);
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) throws IOException {
        log.info(DELETING_CAR_WITH_ID, id, isHardDelete);
        carService.delete(id, isHardDelete);
        log.info(CAR_DELETED_SUCCESSFULLY_WITH_ID, id);
        return answer(HttpStatus.NO_CONTENT);
    }
}
