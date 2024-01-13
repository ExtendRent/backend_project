package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.data.requests.vehicleRequests.CarRequests.AddCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.data.responses.TResponse;
import source_files.services.vehicleService.abstracts.CarService;

@RestController
@RequestMapping("api/car")
@AllArgsConstructor
@Validated
public class CarController {

    private final CarService carService;

    @PostMapping("/add")
    public ResponseEntity<TResponse<?>> addCar(@Valid @RequestBody AddCarRequest addCarRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carService.add(addCarRequest))
                .message("Araba eklendi")
                .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<TResponse<?>> updateCar(@Valid @RequestBody UpdateCarRequest updateCarRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carService.update(updateCarRequest))
                .message("Araba güncellendi")
                .build()
        );
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<TResponse<?>> getById(@Valid @PathVariable int id) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carService.getById(id))
                .message("ID: " + id + " araba görüntülendi")
                .build()
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<TResponse<?>> getAll() throws Exception {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carService.getAll())
                .message("Araba Listesi döndü.")
                .build()
        );
    }


    @GetMapping("/getAllByIsDeletedFalse")
    public ResponseEntity<TResponse<?>> getAllByIsDeletedFalse() throws Exception {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carService.getAllByIsDeletedFalse())
                .message("Silinmeyen Araba Listesi döndü.")
                .build()
        );
    }

    @GetMapping("/getAllByIsDeletedTrue")
    public ResponseEntity<TResponse<?>> getAllByIsDeletedTrue() throws Exception {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carService.getAllByIsDeletedTrue())
                .message("Silinen Araba Listesi döndü.")
                .build()
        );
    }

    @GetMapping("/getAllByIsAvailableTrue")
    public ResponseEntity<TResponse<?>> getAllByIsAvailableTrue() throws Exception {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carService.getAllByIsAvailableTrue())
                .message("Listelenen Araba Listesi döndü.")
                .build()
        );
    }

    @GetMapping("/getAllByIsAvailableFalse")
    public ResponseEntity<TResponse<?>> getAllByIsAvailableFalse() throws Exception {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carService.getAllByIsAvailableFalse())
                .message("Listelenmeyen Araba Listesi döndü.")
                .build()
        );
    }

    @GetMapping("/getAllByColorId/{id}")
    public ResponseEntity<TResponse<?>> getAllByColorId(@Valid @PathVariable int id) throws Exception {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carService.getAllByColorId(id))
                .message("Renge Göre Araba Listesi döndü.")
                .build()
        );
    }

    @GetMapping("/getAllByModelId/{id}")
    public ResponseEntity<TResponse<?>> getAllByModelId(@Valid @PathVariable int id) throws Exception {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carService.getAllByModelId(id))
                .message("Modele Göre Araba Listesi döndü.")
                .build()
        );
    }

    @GetMapping("/getAllByYearBetween/{year1}/{year2}")
    public ResponseEntity<TResponse<?>> getAllByYearBetween(@Valid @PathVariable int year1, @PathVariable int year2) throws Exception {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carService.getAllByYearBetween(year1, year2))
                .message("Araba Listesi döndü.")
                .build()
        );
    }

    @GetMapping("/getAllByBrandId/{id}")
    public ResponseEntity<TResponse<?>> getAllByBrandId(@Valid @PathVariable int id) throws Exception {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carService.getAllByBrandId(id))
                .message("Modele Göre Araba Listesi döndü.")
                .build()
        );
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TResponse<?>> delete(@PathVariable int id, boolean isHardDelete) {

        this.carService.delete(id, isHardDelete);
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .message("Araba silindi.")
                .build()
        );
    }
}
