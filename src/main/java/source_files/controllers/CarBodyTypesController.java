package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.itemDTOs.CarBodyTypeDTO;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.AddCarBodyTypeRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.CarBodyTypeRequests.UpdateCarBodyTypeRequest;
import source_files.data.responses.TResponse;
import source_files.services.vehicleFeaturesServices.abstracts.CarBodyTypeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/carBodyTypes")
@AllArgsConstructor
@Validated
@CrossOrigin
public class CarBodyTypesController {

    private final CarBodyTypeService carBodyTypeService;

    @PostMapping
    public ResponseEntity<Void> createCarBodyType(@RequestBody @Valid AddCarBodyTypeRequest addCarBodyTypeRequest) {
        this.carBodyTypeService.add(addCarBodyTypeRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarBodyTypeDTO>> updateCarBodyType(@RequestBody @Valid UpdateCarBodyTypeRequest updateCarBodyTypeRequest) {
        return new ResponseEntity<>(TResponse.<CarBodyTypeDTO>tResponseBuilder()
                .response(this.carBodyTypeService.update(updateCarBodyTypeRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/")
    public ResponseEntity<TResponse<List<CarBodyTypeDTO>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<CarBodyTypeDTO>>tResponseBuilder()
                .response(this.carBodyTypeService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CarBodyTypeDTO>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<CarBodyTypeDTO>tResponseBuilder()
                .response(this.carBodyTypeService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarBodyTypeDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<CarBodyTypeDTO>>tResponseBuilder()
                .response(this.carBodyTypeService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        this.carBodyTypeService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
