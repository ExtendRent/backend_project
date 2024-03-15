package source_files.controllers.vehicle.carsFeatures;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.controllers.vehicle.dtos.CarSegmentDTO;
import source_files.controllers.vehicle.requests.vehicleFeatures.carSegment.CreateCarSegmentRequest;
import source_files.controllers.vehicle.requests.vehicleFeatures.carSegment.UpdateCarSegmentRequest;
import source_files.data.responses.TResponse;
import source_files.services.vehicleFeatures.abstracts.CarSegmentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/car-segments")
@AllArgsConstructor
@Validated
public class CarSegmentsController {
    private final CarSegmentService segmentService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateCarSegmentRequest createCarSegmentRequest) {
        segmentService.create(createCarSegmentRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarSegmentDTO>> update(@RequestBody UpdateCarSegmentRequest updateCarSegmentRequest) {
        return new ResponseEntity<>(TResponse.<CarSegmentDTO>tResponseBuilder()
                .response(segmentService.update(updateCarSegmentRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CarSegmentDTO>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<CarSegmentDTO>tResponseBuilder()
                .response(segmentService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CarSegmentDTO>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<CarSegmentDTO>>tResponseBuilder()
                .response(segmentService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarSegmentDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<CarSegmentDTO>>tResponseBuilder()
                .response(segmentService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        segmentService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
