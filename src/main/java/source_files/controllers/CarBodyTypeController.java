package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.AddCarBodyTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.UpdateCarBodyTypeRequest;
import source_files.data.responses.TResponse;
import source_files.services.vehicleFeaturesServices.abstracts.CarBodyTypeService;

@RestController
@RequestMapping("api/carBodyType")
@AllArgsConstructor
public class CarBodyTypeController {

    private final CarBodyTypeService carBodyTypeService;

    @PostMapping("/add/carBodyType")
    public ResponseEntity<TResponse<?>> addCarBodyType(@RequestBody @Valid AddCarBodyTypeRequest addCarBodyTypeRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carBodyTypeService.add(addCarBodyTypeRequest))
                .message("Body Type eklendi.")
                .build()
        );
    }

    @PutMapping("/update/carBodyType")
    public ResponseEntity<TResponse<?>> updateCarBodyType(@RequestBody @Valid UpdateCarBodyTypeRequest updateCarBodyTypeRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carBodyTypeService.update(updateCarBodyTypeRequest))
                .message("Body Type güncellendi.")
                .build()
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<TResponse<?>> getAll() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carBodyTypeService.getAll())
                .message("Body Type listesi getirildi.")
                .build()
        );
    }

    @GetMapping("/getById")
    public ResponseEntity<TResponse<?>> getById(@RequestParam int id) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carBodyTypeService.getById(id))
                .message(id + " id' li body type getirildi.")
                .build()
        );
    }

    @GetMapping("/getAllByIsDeletedFalse")
    public ResponseEntity<TResponse<?>> getAllByIsDeletedFalse() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carBodyTypeService.getAllByIsDeletedFalse())
                .message("Mevcut Body Type Listesi Getirildi.")
                .build()
        );
    }

    @GetMapping("/getAllByIsDeletedTrue")
    public ResponseEntity<TResponse<?>> getAllByIsDeletedTrue() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.carBodyTypeService.getAllByIsDeletedTrue())
                .message("Soft Delete ile Silinen Body Type Listesi Getirildi.")
                .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TResponse<?>> delete(@PathVariable int id, boolean isHardDelete) {

        this.carBodyTypeService.delete(id, isHardDelete);
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .message(id + " id' li body type silindi.")
                .build()
        );
    }
}
