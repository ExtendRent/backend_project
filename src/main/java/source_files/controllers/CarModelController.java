package source_files.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.AddCarModelRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.UpdateCarModelRequest;
import source_files.data.responses.TResponse;
import source_files.services.vehicleFeaturesServices.abstracts.CarModelService;

@RestController
@RequestMapping("api/carModel")
@AllArgsConstructor
public class CarModelController {

    private CarModelService carModelService;

    @PostMapping("/add/carModel")
    public ResponseEntity<TResponse<?>> addCarModel(@RequestBody AddCarModelRequest addCarModelRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carModelService.add(addCarModelRequest))
                .message("Araba modeli eklendi.")
                .build()
        );
    }

    @PutMapping("/update/carModel")
    public ResponseEntity<TResponse<?>> updateCarModel(@RequestBody UpdateCarModelRequest updateCarModelRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carModelService.update(updateCarModelRequest))
                .message("Araba modeli güncellendi.")
                .build()
        );
    }

    @GetMapping("/getByCarModelId")
    public ResponseEntity<TResponse<?>> getByCarModelId(@RequestParam int id) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carModelService.getById(id))
                .message(id + " id'li araba modeli getirildi.")
                .build()
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<TResponse<?>> getAll() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carModelService.getAll())
                .message("Araba modeli listesi getirildi.")
                .build()
        );
    }

    @GetMapping("/getByModelName")
    public ResponseEntity<TResponse<?>> getByModelName(@RequestParam String modelName) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carModelService.getByModelName(modelName))
                .message("Model görüntülendi")
                .build()
        );
    }

    @GetMapping("/getByBrandId")
    public ResponseEntity<TResponse<?>> getByBrandId(@RequestParam int brandId) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carModelService.getAllByBrandId(brandId))
                .message("Model görüntülendi")
                .build()
        );
    }

    @GetMapping("/getAllByIsDeletedFalse")
    public ResponseEntity<TResponse<?>> getAllByIsDeletedFalse() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carModelService.getAllByIsDeletedFalse())
                .message("Mevcut Model Listesi Getirildi.")
                .build()
        );
    }

    @GetMapping("/getAllByIsDeletedTrue")
    public ResponseEntity<TResponse<?>> getAllByIsDeletedTrue() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carModelService.getAllByIsDeletedTrue())
                .message("Soft Delete ile Silinen Model Listesi Getirildi.")
                .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TResponse<?>> delete(@PathVariable int id, boolean isHardDelete) {
        this.carModelService.delete(id, isHardDelete);
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .message(id + " id li araba modeli silindi.")
                .build()
        );
    }
}
