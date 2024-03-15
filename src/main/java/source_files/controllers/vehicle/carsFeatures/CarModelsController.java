package source_files.controllers.vehicle.carsFeatures;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.controllers.vehicle.dtos.CarModelDTO;
import source_files.controllers.vehicle.requests.vehicleFeatures.carModel.CreateCarModelRequest;
import source_files.controllers.vehicle.requests.vehicleFeatures.carModel.UpdateCarModelRequest;
import source_files.data.responses.TResponse;
import source_files.services.vehicleFeatures.abstracts.CarModelService;

import java.util.List;

@RestController
@RequestMapping("api/v1/carModels")
@AllArgsConstructor
@Validated
@CrossOrigin
public class CarModelsController {

    private CarModelService carModelService;

    @PostMapping
    public ResponseEntity<Void> createCarModel(@RequestBody CreateCarModelRequest createCarModelRequest) {
        this.carModelService.create(createCarModelRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarModelDTO>> updateCarModel(@RequestBody UpdateCarModelRequest updateCarModelRequest) {
        return new ResponseEntity<>(TResponse.<CarModelDTO>tResponseBuilder()
                .response(this.carModelService.update(updateCarModelRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CarModelDTO>> getByCarModelId(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<CarModelDTO>tResponseBuilder()
                .response(this.carModelService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CarModelDTO>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<CarModelDTO>>tResponseBuilder()
                .response(this.carModelService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/brands/{brandId}")
    public ResponseEntity<TResponse<?>> getByBrandId(@PathVariable int brandId) {
        return new ResponseEntity<>(TResponse.tResponseBuilder()
                .response(this.carModelService.getAllByBrandId(brandId))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarModelDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<CarModelDTO>>tResponseBuilder()
                .response(this.carModelService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        this.carModelService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
