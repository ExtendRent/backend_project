package src.controller.vehicle.features.common.shift;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.features.common.shift.requests.CreateShiftTypeRequest;
import src.controller.vehicle.features.common.shift.requests.UpdateShiftTypeRequest;
import src.controller.vehicle.features.common.shift.responses.ShiftTypeResponse;
import src.service.vehicle.features.common.shift.ShiftTypeService;

import java.util.List;

import static src.controller.vehicle.features.common.shift.LogConstant.*;

@RestController
@RequestMapping("api/v1/gearshifts")
@RequiredArgsConstructor
@Validated
public class ShiftController {

    private final Logger logger = LoggerFactory.getLogger(ShiftController.class);
    private final ShiftTypeService shiftTypeService;

    @PostMapping
    public ResponseEntity<Void> createShift(@RequestBody @Valid CreateShiftTypeRequest createShiftTypeRequest) {
        logger.info(CREATING_NEW_SHIFT, createShiftTypeRequest.toString());
        shiftTypeService.create(createShiftTypeRequest);
        logger.info(SHIFT_SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<ShiftTypeResponse>> updateShift(@RequestBody @Valid UpdateShiftTypeRequest updateShiftTypeRequest) {
        logger.info(UPDATING_SHIFT, updateShiftTypeRequest.toString());
        ShiftTypeResponse updatedShift = this.shiftTypeService.update(updateShiftTypeRequest);
        logger.info(SHIFT_UPDATED, updatedShift.toString());
        return new ResponseEntity<>(TResponse.<ShiftTypeResponse>tResponseBuilder()
                .response(updatedShift).build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<ShiftTypeResponse>> getById(@PathVariable int id) {
        logger.info(GETTING_SHIFT_DETAILS, id);
        ShiftTypeResponse shift = this.shiftTypeService.getById(id);
        logger.info(RETRIEVED_SHIFT_DETAILS, shift.toString());
        return new ResponseEntity<>(TResponse.<ShiftTypeResponse>tResponseBuilder()
                .response(shift).build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<ShiftTypeResponse>>> getAll() {
        logger.info(RETRIEVING_ALL_SHIFTS);
        List<ShiftTypeResponse> shifts = this.shiftTypeService.getAll();
        logger.info(RETRIEVED_ALL_SHIFTS, shifts.size());
        return new ResponseEntity<>(TResponse.<List<ShiftTypeResponse>>tResponseBuilder()
                .response(shifts).build(), HttpStatus.OK);
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<ShiftTypeResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        logger.info(RETRIEVING_SHIFTS_BY_DELETED_STATE, isDeleted);
        List<ShiftTypeResponse> shifts = this.shiftTypeService.getAllByDeletedState(isDeleted);
        logger.info(RETRIEVED_SHIFTS_BY_DELETED_STATE, shifts.size());
        return new ResponseEntity<>(TResponse.<List<ShiftTypeResponse>>tResponseBuilder()
                .response(shifts).build(), HttpStatus.OK);
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        logger.info(DELETING_SHIFT_WITH_ID, id, isHardDelete);
        this.shiftTypeService.delete(id, isHardDelete);
        logger.info(SHIFT_DELETED_SUCCESSFULLY_WITH_ID, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
