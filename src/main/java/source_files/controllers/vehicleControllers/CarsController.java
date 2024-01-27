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
    public ResponseEntity<Void> createCar(@Valid @RequestBody CreateCarRequest createCarRequest) {
        this.carService.create(createCarRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarDTO>> updateCar(@Valid @RequestBody UpdateCarRequest updateCarRequest) {
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

    @GetMapping("/filtered")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllFiltered(
            @RequestParam(name = "customerId", required = false) Integer customerId,
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
            @RequestParam(name = "statusId", required = false) Integer statusId

    ) {
        List<CarDTO> filteredCars = carService.getAllFiltered(
                customerId, startDate, endDate,
                startPrice, endPrice,
                isDeleted, statusId,
                colorId, seat, luggage, modelId,
                startYear, endYear, brandId,
                fuelTypeId, shiftTypeId
        );
        return new ResponseEntity<>(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(filteredCars)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "/{customerId}")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllWithLogin(
            @RequestParam(name = "customerId", required = false) Integer customerId) {
        return new ResponseEntity<>(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllWithLogin(customerId))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByDrivingLicenseSuitable(@PathVariable int customerId) {
        return new ResponseEntity<>(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByIsDrivingLicenseSuitable(customerId))
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

    @GetMapping(params = {"startPrice", "endPrice"})
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByPriceBetween(
            @RequestParam(name = "startPrice", required = false) Integer startPrice,
            @RequestParam(name = "endPrice", required = false) Integer endPrice) {

        try {
            // Başarılı yanıtı oluştur
            List<CarDTO> carDTOList = this.carService.getAllByRentalPriceBetween(startPrice, endPrice);
            return new ResponseEntity<>(TResponse.<List<CarDTO>>tResponseBuilder()
                    .response(carDTOList)
                    .build(), HttpStatus.OK
            );
        } catch (NumberFormatException e) {
            // Sayıya çeviremezse hata mesajı döndür
            return ResponseEntity.badRequest().body(TResponse.<List<CarDTO>>tResponseBuilder()
                    .build()
            );
        }
    }


    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "statusId")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByAvailableState(
            @RequestParam(value = "isAvailable", required = false) Integer statusId) {
        return new ResponseEntity<>(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByStatus(statusId))
                .build(), HttpStatus.OK
        );
    }


    @GetMapping("/colors/{colorId}")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByColorId(@PathVariable int colorId) {
        return new ResponseEntity<>(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByColorId(colorId))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/models/{modelId}")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByModelId(@PathVariable int modelId) {
        return new ResponseEntity<>(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByModelId(modelId))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = {"startYear", "endYear"})
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByYearBetween(
            @RequestParam(name = "startYear", required = false) int startYear,
            @RequestParam(value = "endYear", required = false) int endYear) {

        return new ResponseEntity<>(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByYearBetween(startYear, endYear))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/brands/{brandId}")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByBrandId(@PathVariable int brandId) {
        return new ResponseEntity<>(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByBrandId(brandId))
                .build(), HttpStatus.OK
        );
    }


    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        this.carService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
