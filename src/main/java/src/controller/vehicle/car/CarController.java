package src.controller.vehicle.car;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.car.request.CreateCarRequest;
import src.controller.vehicle.car.request.UpdateCarRequest;
import src.controller.vehicle.car.response.CarResponse;
import src.service.vehicle.car.CarService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static src.controller.vehicle.car.LogConstant.*;

@RestController
@RequestMapping("api/v1/cars")
@RequiredArgsConstructor

public class CarController {

    private static final Logger logger = LoggerFactory.getLogger(CarController.class);
    private final CarService carService;

    @PostMapping
    public ResponseEntity<Void> createCar(@RequestBody @Valid CreateCarRequest createCarRequest) throws IOException {
        logger.info(CREATING_NEW_CAR, createCarRequest);
        this.carService.create(createCarRequest);
        logger.info(CAR_SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarResponse>> updateCar(@Valid @RequestBody UpdateCarRequest updateCarRequest) throws IOException {
        logger.info(UPDATING_CAR, updateCarRequest);
        CarResponse updatedCar = this.carService.update(updateCarRequest);
        logger.info(CAR_UPDATED, updatedCar);
        return new ResponseEntity<>(TResponse.<CarResponse>tResponseBuilder()
                .response(updatedCar)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CarResponse>> getById(@PathVariable int id) {
        logger.info(GETTING_CAR_DETAILS, id);
        CarResponse car = this.carService.getById(id);
        logger.info(RETRIEVED_CAR_DETAILS, car);
        return new ResponseEntity<>(TResponse.<CarResponse>tResponseBuilder()
                .response(car)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CarResponse>>> getAll() {
        logger.info(RETRIEVING_ALL_CARS);
        List<CarResponse> cars = this.carService.getAll();
        logger.info(RETRIEVED_ALL_CARS, cars.size());
        return new ResponseEntity<>(TResponse.<List<CarResponse>>tResponseBuilder()
                .response(cars)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/count/{isDeleted}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable boolean isDeleted) {
        logger.info(RETRIEVING_CAR_COUNT_BY_DELETED_STATE, isDeleted);
        int count = this.carService.getCountByDeletedState(isDeleted);
        logger.info(RETRIEVED_CAR_COUNT_BY_DELETED_STATE, count);
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(count)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/countByStatus/{statusId}")
    public ResponseEntity<TResponse<Integer>> getCountByStatusId(@PathVariable int statusId) {
        logger.info(RETRIEVING_CAR_COUNT_BY_STATUS_ID, statusId);
        int count = this.carService.getCountByStatusId(statusId);
        logger.info(RETRIEVED_CAR_COUNT_BY_STATUS_ID, count);
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(count)
                .build(), HttpStatus.OK
        );
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
        logger.info(FILTERING_CARS);
        List<CarResponse> filteredCars = carService.getAllFiltered(
                customerId, licenseSuitable, startDate,
                endDate, startPrice,
                endPrice, isDeleted,
                statusId, colorId, seat, luggage,
                modelId, startYear, endYear,
                brandId, fuelTypeId,
                shiftTypeId, segmentId);
        logger.info(FILTERED_CARS, filteredCars.size());
        return new ResponseEntity<>(TResponse.<List<CarResponse>>tResponseBuilder()
                .response(filteredCars)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = {"startDate", "endDate"})
    public ResponseEntity<TResponse<List<CarResponse>>> getAllByAvailabilityBetween(
            @RequestParam(name = "startDate", required = false) LocalDate startDate,
            @RequestParam(name = "endDate", required = false) LocalDate endDate) {
        logger.info(RETRIEVING_CARS_BY_AVAILABILITY, startDate, endDate);
        List<CarResponse> cars = this.carService.getAllByAvailabilityBetween(startDate, endDate);
        logger.info(RETRIEVED_CARS_BY_AVAILABILITY, cars.size());
        return new ResponseEntity<>(TResponse.<List<CarResponse>>tResponseBuilder()
                .response(cars)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        logger.info(RETRIEVING_ALL_CARS_BY_DELETED_STATE, isDeleted);
        List<CarResponse> cars = this.carService.getAllByDeletedState(isDeleted);
        logger.info(RETRIEVED_ALL_CARS_BY_DELETED_STATE, cars.size());
        return new ResponseEntity<>(TResponse.<List<CarResponse>>tResponseBuilder()
                .response(cars)
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) throws IOException {
        logger.info(DELETING_CAR_WITH_ID, id, isHardDelete);
        this.carService.delete(id, isHardDelete);
        logger.info(CAR_DELETED_SUCCESSFULLY_WITH_ID, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
