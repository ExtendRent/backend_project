package src.controller.vehicle.features.car.segment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.features.car.segment.request.CreateCarSegmentRequest;
import src.controller.vehicle.features.car.segment.request.UpdateCarSegmentRequest;
import src.controller.vehicle.features.car.segment.response.CarSegmentResponse;
import src.service.vehicle.features.car.segment.CarSegmentService;

import java.util.List;

import static src.controller.vehicle.features.car.segment.LogConstant.*;


@RestController
@Slf4j
@RequestMapping("api/v1/car-segments")
@RequiredArgsConstructor
public class CarSegmentController {
    private final CarSegmentService segmentService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateCarSegmentRequest createCarSegmentRequest) {
        log.info(CREATING_NEW_CAR_SEGMENT, createCarSegmentRequest.toString());
        segmentService.create(createCarSegmentRequest);
        log.info(CAR_SEGMENT_SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarSegmentResponse>> update(@RequestBody UpdateCarSegmentRequest updateCarSegmentRequest) {
        log.info(UPDATING_CAR_SEGMENT, updateCarSegmentRequest.toString());
        CarSegmentResponse updatedSegment = segmentService.update(updateCarSegmentRequest);
        log.info(CAR_SEGMENT_UPDATED, updatedSegment.toString());
        return new ResponseEntity<>(TResponse.<CarSegmentResponse>tResponseBuilder()
                .response(updatedSegment)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CarSegmentResponse>> getById(@PathVariable int id) {
        log.info(GETTING_CAR_SEGMENT_DETAILS, id);
        CarSegmentResponse segment = segmentService.getById(id);
        log.info(RETRIEVED_CAR_SEGMENT_DETAILS, segment.toString());
        return new ResponseEntity<>(TResponse.<CarSegmentResponse>tResponseBuilder()
                .response(segment)
                .build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CarSegmentResponse>>> getAll() {
        log.info(RETRIEVING_ALL_CAR_SEGMENTS);
        List<CarSegmentResponse> segments = segmentService.getAll();
        log.info(RETRIEVED_ALL_CAR_SEGMENTS, segments.size());
        return new ResponseEntity<>(TResponse.<List<CarSegmentResponse>>tResponseBuilder()
                .response(segments)
                .build(), HttpStatus.OK);
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarSegmentResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        log.info(RETRIEVING_CAR_SEGMENTS_BY_DELETED_STATE, isDeleted);
        List<CarSegmentResponse> segments = segmentService.getAllByDeletedState(isDeleted);
        log.info(RETRIEVED_CAR_SEGMENTS_BY_DELETED_STATE, segments.size());
        return new ResponseEntity<>(TResponse.<List<CarSegmentResponse>>tResponseBuilder()
                .response(segments)
                .build(), HttpStatus.OK);
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        log.info(DELETING_CAR_SEGMENT_WITH_ID, id, isHardDelete);
        segmentService.delete(id, isHardDelete);
        log.info(CAR_SEGMENT_DELETED_SUCCESSFULLY_WITH_ID, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
