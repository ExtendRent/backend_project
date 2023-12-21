package source_files.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.CarModelRequests.AddCarModelRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.CarModelRequests.UpdateCarModelRequest;
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

    @GetMapping("/getByCarModelId/{id}")
    public ResponseEntity<TResponse<?>> getByCarModelId(@PathVariable int id) {
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
