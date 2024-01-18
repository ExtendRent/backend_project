package source_files.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.itemDTOs.CarModelDTO;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.AddCarModelRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarModelRequests.UpdateCarModelRequest;
import source_files.data.responses.TResponse;
import source_files.services.vehicleFeaturesServices.abstracts.CarModelService;

import java.util.List;

@RestController
@RequestMapping("api/v1/carModels")
@AllArgsConstructor
public class CarModelController {

    private CarModelService carModelService;

    @PostMapping
    public ResponseEntity<HttpStatus> addCarModel(@RequestBody AddCarModelRequest addCarModelRequest) {
        this.carModelService.add(addCarModelRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarModelDTO>> updateCarModel(@RequestBody UpdateCarModelRequest updateCarModelRequest) {
        return ResponseEntity.ok(TResponse.<CarModelDTO>tResponseBuilder()
                .response(this.carModelService.update(updateCarModelRequest))
                .message("Araba modeli güncellendi.")
                .build()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<TResponse<CarModelDTO>> getByCarModelId(@PathVariable int id) {
        return ResponseEntity.ok(TResponse.<CarModelDTO>tResponseBuilder()
                .response(this.carModelService.getById(id))
                .message(id + " id'li araba modeli getirildi.")
                .build()
        );
    }

    @GetMapping("/")
    public ResponseEntity<TResponse<List<CarModelDTO>>> getAll() {
        return ResponseEntity.ok(TResponse.<List<CarModelDTO>>tResponseBuilder()
                .response(this.carModelService.getAll())
                .message("Araba modeli listesi getirildi.")
                .build()
        );
    }

    @GetMapping("/models/{modelName}")
    public ResponseEntity<TResponse<?>> getByModelName(@PathVariable String modelName) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.carModelService.getByModelName(modelName))
                .message("Model görüntülendi")
                .build()
        );
    }

    @GetMapping("/brands/{brandId}")
    public ResponseEntity<TResponse<?>> getByBrandId(@PathVariable int brandId) {
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
                .message("Silinene göre model Listesi döndü.")
                .build()
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<HttpStatus> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        this.carModelService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
