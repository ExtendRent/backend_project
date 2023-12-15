package source_files.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.requests.vehicleRequests.CarRequests.AddCarRequest;
import source_files.data.requests.vehicleRequests.CarRequests.UpdateCarRequest;
import source_files.data.responses.TResponse;
import source_files.services.vehicleService.abstracts.CarService;

@RestController
@RequestMapping("api/car")
@AllArgsConstructor
public class CarController {

    private final CarService carService;

    @PostMapping("/add/car")
    public ResponseEntity<TResponse<?>> addCar(@RequestBody AddCarRequest addCarRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder() //TODO Builder okunmuyor. çözülecek.
                .isSuccess(true)
                .response(this.carService.add(addCarRequest))
                .message("Araba eklendi")
                .build()
        );
    }
    @PutMapping("/update/car")
    public  ResponseEntity<TResponse<?>> updateCar(@RequestBody UpdateCarRequest updateCarRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carService.update(updateCarRequest))
                .message("Araba güncellendi")
                .build()
        );
    }
}
