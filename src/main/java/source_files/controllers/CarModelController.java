package source_files.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.itemDTOs.CarModelDTO;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.AddCarModelRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.UpdateCarModelRequest;
import source_files.data.responses.TResponse;
import source_files.services.vehicleFeaturesServices.abstracts.CarModelService;

import java.util.List;

@RestController
@RequestMapping("api/carModel")
@AllArgsConstructor
public class CarModelController {

    private CarModelService carModelService;

    @PostMapping("/add/carModel")
    public ResponseEntity<TResponse<?>> addCarModel(@RequestBody AddCarModelRequest addCarModelRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.carModelService.add(addCarModelRequest))
                .message("Araba modeli eklendi.")
                .build()
        );
    }

    @PutMapping("/update/carModel")
    public ResponseEntity<TResponse<?>> updateCarModel(@RequestBody UpdateCarModelRequest updateCarModelRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.carModelService.update(updateCarModelRequest))
                .message("Araba modeli güncellendi.")
                .build()
        );
    }

    @GetMapping("/getByCarModelId")
    public ResponseEntity<TResponse<?>> getByCarModelId(@RequestParam int id) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.carModelService.getById(id))
                .message(id + " id'li araba modeli getirildi.")
                .build()
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<TResponse<?>> getAll() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.carModelService.getAll())
                .message("Araba modeli listesi getirildi.")
                .build()
        );
    }

    @GetMapping("/getByModelName")
    public ResponseEntity<TResponse<?>> getByModelName(@RequestParam String modelName) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.carModelService.getByModelName(modelName))
                .message("Model görüntülendi")
                .build()
        );
    }

    @GetMapping("/getByBrandId")
    public ResponseEntity<TResponse<?>> getByBrandId(@RequestParam int brandId) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.carModelService.getAllByBrandId(brandId))
                .message("Model görüntülendi")
                .build()
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarModelDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return ResponseEntity.ok(TResponse.<List<CarModelDTO>>tResponseBuilder()
                .response(this.carModelService.getAllByDeletedState(isDeleted))
                .message("Silinmeyen Araba Listesi döndü.")
                .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TResponse<?>> delete(@PathVariable int id, boolean isHardDelete) {
        this.carModelService.delete(id, isHardDelete);
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .message(id + " id li araba modeli silindi.")
                .build()
        );
    }
}
