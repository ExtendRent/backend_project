package src.controller.vehicle.features.car.segment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.features.car.segment.requests.CreateCarSegmentRequest;
import src.controller.vehicle.features.car.segment.requests.UpdateCarSegmentRequest;
import src.controller.vehicle.features.car.segment.responses.CarSegmentResponse;
import src.service.vehicle.features.car.segment.CarSegmentService;

import java.util.List;

@RestController
@RequestMapping("api/v1/car-segments")
@RequiredArgsConstructor
@Validated
public class CarSegmentsController {
    private final CarSegmentService segmentService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateCarSegmentRequest createCarSegmentRequest) {
        segmentService.create(createCarSegmentRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarSegmentResponse>> update(@RequestBody UpdateCarSegmentRequest updateCarSegmentRequest) {
        return new ResponseEntity<>(TResponse.<CarSegmentResponse>tResponseBuilder()
                .response(segmentService.update(updateCarSegmentRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CarSegmentResponse>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<CarSegmentResponse>tResponseBuilder()
                .response(segmentService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CarSegmentResponse>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<CarSegmentResponse>>tResponseBuilder()
                .response(segmentService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarSegmentResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<CarSegmentResponse>>tResponseBuilder()
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
