package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.vehicleDTOs.CarDTO;
import source_files.data.requests.vehicleRequests.CarRequests.AddCarRequest;
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
    public ResponseEntity<HttpStatus> addCar(@Valid @RequestBody AddCarRequest addCarRequest) {
        this.carService.add(addCarRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarDTO>> updateCar(@Valid @RequestBody UpdateCarRequest updateCarRequest) {
        return ResponseEntity.ok(TResponse.<CarDTO>tResponseBuilder()
                .response(this.carService.update(updateCarRequest))
                .message("Araba güncellendi")
                .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CarDTO>> getById(@PathVariable int id) {
        return ResponseEntity.ok(TResponse.<CarDTO>tResponseBuilder()
                .response(this.carService.getById(id))
                .message("ID: " + id + " araba görüntülendi")
                .build()
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CarDTO>>> getAll() {
        return ResponseEntity.ok(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAll())
                .message("Araba Listesi döndü.")
                .build()
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
            @RequestParam(name = "colorId", required = false) String fuelType,
            @RequestParam(name = "colorId", required = false) String shiftType,
            @RequestParam(name = "colorId", required = false) Integer seat,
            @RequestParam(name = "colorId", required = false) Integer luggage,
            @RequestParam(name = "startPrice", required = false) Integer startPrice,
            @RequestParam(name = "endPrice", required = false) Integer endPrice,
            @RequestParam(name = "startYear", required = false) Integer startYear,
            @RequestParam(name = "endYear", required = false) Integer endYear,
            @RequestParam(name = "isDeleted", required = false) Boolean isDeleted,
            @RequestParam(name = "isAvailable", required = false) Boolean isAvailable
    ) {
        List<CarDTO> filteredCars = carService.getAllFiltered(
                customerId, startDate, endDate,
                startPrice, endPrice,
                isDeleted, isAvailable,
                colorId, fuelType,
                shiftType, seat, luggage,
                modelId, startYear,
                endYear, brandId);
        return ResponseEntity.ok(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(filteredCars)
                .message("Filtrelenmiş Araba Listesi döndü.")
                .build()
        );
    }

    @GetMapping(params = "{customerId}")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllWithLogin(
            @RequestParam(name = "customerId", required = false) Integer customerId) {
        return ResponseEntity.ok(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllWithLogin(customerId))
                .message("Araba Listesi döndü.")
                .build()
        );
    }

    @GetMapping("/customers/{customerId}")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByDrivingLicenseSuitable(@PathVariable int customerId) {
        return ResponseEntity.ok(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByIsDrivingLicenseSuitable(customerId))
                .message("Araba Listesi döndü.")
                .build()
        );
    }

    @GetMapping(params = {"startDate", "endDate"})
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByAvailabilityBetween(
            @RequestParam(name = "startDate", required = false) LocalDate startDate
            , @RequestParam(name = "endDate", required = false) LocalDate endDate) {

        return ResponseEntity.ok(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByAvailabilityBetween(startDate, endDate))
                .message("Araba Listesi döndü.")
                .build()
        );
    }

    @GetMapping(params = {"startPrice", "endPrice"})
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByPriceBetween(
            @RequestParam(name = "startPrice", required = false) Integer startPrice,
            @RequestParam(name = "endPrice", required = false) Integer endPrice) {

        try {
            // Başarılı yanıtı oluştur
            List<CarDTO> carDTOList = this.carService.getAllByRentalPriceBetween(startPrice, endPrice);
            return ResponseEntity.ok(TResponse.<List<CarDTO>>tResponseBuilder()
                    .response(carDTOList)
                    .message("Araba Listesi döndü.")
                    .build()
            );
        } catch (NumberFormatException e) {
            // Sayıya çeviremezse hata mesajı döndür
            return ResponseEntity.badRequest().body(TResponse.<List<CarDTO>>tResponseBuilder()
                    .message("Başlangıç ve bitiş fiyatları geçerli sayısal değerler olmalıdır.")
                    .build()
            );
        }
    }


    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return ResponseEntity.ok(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByDeletedState(isDeleted))
                .message("Silinmeyen Araba Listesi döndü.")
                .build()
        );
    }

    @GetMapping(params = "isAvailable")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByAvailableState(
            @RequestParam(value = "isAvailable", required = false) boolean isAvailable) {
        return ResponseEntity.ok(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByAvailableState(isAvailable))
                .message("Listelenen Araba Listesi döndü.")
                .build()
        );
    }


    @GetMapping("/colors/{colorId}")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByColorId(@PathVariable int colorId) {
        return ResponseEntity.ok(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByColorId(colorId))
                .message("Renge Göre Araba Listesi döndü.")
                .build()
        );
    }

    @GetMapping("/models/{modelId}")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByModelId(@PathVariable int modelId) {
        return ResponseEntity.ok(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByModelId(modelId))
                .message("Modele Göre Araba Listesi döndü.")
                .build()
        );
    }

    @GetMapping(params = {"startYear", "endYear"})
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByYearBetween(
            @RequestParam(name = "startYear", required = false) int startYear,
            @RequestParam(value = "endYear", required = false) int endYear) {

        return ResponseEntity.ok(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByYearBetween(startYear, endYear))
                .message("Araba Listesi döndü.")
                .build()
        );
    }

    @GetMapping("/brands/{brandId}")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByBrandId(@PathVariable int brandId) {
        return ResponseEntity.ok(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByBrandId(brandId))
                .message("Modele Göre Araba Listesi döndü.")
                .build()
        );
    }


    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<HttpStatus> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        this.carService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
