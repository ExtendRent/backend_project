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

import java.util.List;

@RestController
@RequestMapping("api/v1/cars")
@AllArgsConstructor
@Validated
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<TResponse<CarDTO>> addCar(@Valid @RequestBody AddCarRequest addCarRequest) {
        this.carService.add(addCarRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<?>> updateCar(@Valid @RequestBody UpdateCarRequest updateCarRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.carService.update(updateCarRequest))
                .message("Araba güncellendi")
                .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<?>> getById(@PathVariable int id) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.carService.getById(id))
                .message("ID: " + id + " araba görüntülendi")
                .build()
        );
    }

    @GetMapping("/")
    public ResponseEntity<TResponse<?>> getAll() throws Exception {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.carService.getAll())
                .message("Araba Listesi döndü.")
                .build()
        );
    }


    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByDeletedState(@RequestParam(value = "isDeleted") boolean isDeleted) {
        return ResponseEntity.ok(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByDeletedState(isDeleted))
                .message("Silinmeyen Araba Listesi döndü.")
                .build()
        );
    }

    @GetMapping(params = "isAvailable")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByAvailableState(@RequestParam(value = "isAvailable") boolean isAvailable) {
        return ResponseEntity.ok(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByAvailableState(isAvailable))
                .message("Listelenen Araba Listesi döndü.")
                .build()
        );
    }


    @GetMapping(params = "colorId")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByColorId(@RequestParam(value = "colorId") int colorId) {
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

    @GetMapping(params = {"startDate", "endDate"})
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByYearBetween(
            @RequestParam(name = "startDate") int startDate, @RequestParam(value = "endDate") int endDate) {

        return ResponseEntity.ok(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByYearBetween(startDate, endDate))
                .message("Araba Listesi döndü.")
                .build()
        );
    }

    @GetMapping("/brands/{branId}")
    public ResponseEntity<TResponse<List<CarDTO>>> getAllByBrandId(@PathVariable int brandId) {
        return ResponseEntity.ok(TResponse.<List<CarDTO>>tResponseBuilder()
                .response(this.carService.getAllByBrandId(brandId))
                .message("Modele Göre Araba Listesi döndü.")
                .build()
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TResponse<?>> delete(@PathVariable int id, boolean isHardDelete) {

        this.carService.delete(id, isHardDelete);
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .message("Araba silindi.")
                .build()
        );
    }
}
