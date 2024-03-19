package src.controller.vehicle.features.car.segment;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import static src.controller.vehicle.features.car.segment.LogConstant.*;


@RestController
@RequestMapping("api/v1/car-segments")
@RequiredArgsConstructor
@Validated
public class CarSegmentsController {
    private static final Logger logger = LoggerFactory.getLogger(CarSegmentsController.class);
    private final CarSegmentService segmentService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateCarSegmentRequest createCarSegmentRequest) {
        logger.info(CREATING_NEW_CAR_SEGMENT, createCarSegmentRequest.toString());
        segmentService.create(createCarSegmentRequest);
        logger.info(CAR_SEGMENT_SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<CarSegmentResponse>> update(@RequestBody UpdateCarSegmentRequest updateCarSegmentRequest) {
        logger.info(UPDATING_CAR_SEGMENT, updateCarSegmentRequest.toString());
        CarSegmentResponse updatedSegment = segmentService.update(updateCarSegmentRequest);
        logger.info(CAR_SEGMENT_UPDATED, updatedSegment.toString());
        return new ResponseEntity<>(TResponse.<CarSegmentResponse>tResponseBuilder()
                .response(updatedSegment)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<CarSegmentResponse>> getById(@PathVariable int id) {
        logger.info(GETTING_CAR_SEGMENT_DETAILS, id);
        CarSegmentResponse segment = segmentService.getById(id);
        logger.info(RETRIEVED_CAR_SEGMENT_DETAILS, segment.toString());
        return new ResponseEntity<>(TResponse.<CarSegmentResponse>tResponseBuilder()
                .response(segment)
                .build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<CarSegmentResponse>>> getAll() {
        logger.info(RETRIEVING_ALL_CAR_SEGMENTS);
        List<CarSegmentResponse> segments = segmentService.getAll();
        logger.info(RETRIEVED_ALL_CAR_SEGMENTS, segments.size());
        return new ResponseEntity<>(TResponse.<List<CarSegmentResponse>>tResponseBuilder()
                .response(segments)
                .build(), HttpStatus.OK);
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<CarSegmentResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        logger.info(RETRIEVING_CAR_SEGMENTS_BY_DELETED_STATE, isDeleted);
        List<CarSegmentResponse> segments = segmentService.getAllByDeletedState(isDeleted);
        logger.info(RETRIEVED_CAR_SEGMENTS_BY_DELETED_STATE, segments.size());
        return new ResponseEntity<>(TResponse.<List<CarSegmentResponse>>tResponseBuilder()
                .response(segments)
                .build(), HttpStatus.OK);
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        logger.info(DELETING_CAR_SEGMENT_WITH_ID, id, isHardDelete);
        segmentService.delete(id, isHardDelete);
        logger.info(CAR_SEGMENT_DELETED_SUCCESSFULLY_WITH_ID, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
