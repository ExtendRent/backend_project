package src.controller.vehicle.features.car.body;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.features.car.body.requests.CreateCarBodyTypeRequest;
import src.controller.vehicle.features.car.body.requests.UpdateCarBodyTypeRequest;
import src.controller.vehicle.features.car.body.responses.CarBodyTypeResponse;
import src.service.vehicle.features.car.body.CarBodyTypeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/carBodyTypes")
@RequiredArgsConstructor
@Validated

public class CarBodyTypesController {

    private final CarBodyTypeService carBodyTypeService;

    @PostMapping
    public ResponseEntity<Void> createCarBodyType(@RequestBody @Valid CreateCarBodyTypeRequest createCarBodyTypeRequest) {
        this.carBodyTypeService.create(createCarBodyTypeRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarBodyTypeResponse>> updateCarBodyType(@RequestBody @Valid UpdateCarBodyTypeRequest updateCarBodyTypeRequest) {
        return new ResponseEntity<>(TResponse.<CarBodyTypeResponse>tResponseBuilder()
                .response(this.carBodyTypeService.update(updateCarBodyTypeRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CarBodyTypeResponse>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<CarBodyTypeResponse>>tResponseBuilder()
                .response(this.carBodyTypeService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CarBodyTypeResponse>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<CarBodyTypeResponse>tResponseBuilder()
                .response(this.carBodyTypeService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarBodyTypeResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<CarBodyTypeResponse>>tResponseBuilder()
                .response(this.carBodyTypeService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        this.carBodyTypeService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
