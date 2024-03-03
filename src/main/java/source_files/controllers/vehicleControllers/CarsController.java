package source_files.controllers.vehicleControllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.requests.vehicleRequests.CarRequests.CreateCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.data.responses.TResponse;
import source_files.services.vehicleService.abstracts.CarService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/cars")
@AllArgsConstructor
@Validated
@CrossOrigin
public class CarsController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<Void> createCar(@RequestBody CreateCarRequest createCarRequest) throws IOException {
        this.carService.create(createCarRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PutMapping
    public ResponseEntity<TResponse<CarDTO>> updateCar(@Valid @RequestBody UpdateCarRequest updateCarRequest) throws IOException {
        return new ResponseEntity<>(TResponse.<CarDTO>tResponseBuilder()
                .response(this.carService.update(updateCarRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CarDTO>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<CarDTO>tResponseBuilder()
                .response(this.carService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CarDTO>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/count/{isDeleted}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(this.carService.getCountByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/countByStatus/{statusId}")
    public ResponseEntity<TResponse<Integer>> getCountByDeletedState(@PathVariable int statusId) {
        return new ResponseEntity<>(TResponse.<Integer>tResponseBuilder()
                .response(this.carService.getCountByStatusId(statusId))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/filter")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllFiltered(
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
        List<CarDTO> filteredCars = carService.getAllFiltered(
                customerId, licenseSuitable, startDate,
                endDate, startPrice,
                endPrice, isDeleted,
                statusId, colorId, seat, luggage,
                modelId, startYear, endYear,
                brandId, fuelTypeId,
                shiftTypeId, segmentId);
        return new ResponseEntity<>(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(filteredCars)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = {"startDate", "endDate"})
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByAvailabilityBetween(
            @RequestParam(name = "startDate", required = false) LocalDate startDate
            , @RequestParam(name = "endDate", required = false) LocalDate endDate) {

        return new ResponseEntity<>(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByAvailabilityBetween(startDate, endDate))
                .build(), HttpStatus.OK
        );
    }


    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) throws IOException {
        this.carService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
