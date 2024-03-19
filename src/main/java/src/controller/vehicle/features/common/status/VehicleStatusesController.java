package src.controller.vehicle.features.common.status;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.features.common.status.requests.UpdateVehicleStatusRequest;
import src.controller.vehicle.features.common.status.responses.VehicleStatusResponse;
import src.service.vehicle.features.common.status.VehicleStatusService;
import src.service.vehicle.features.common.status.model.DefaultVehicleStatus;

import java.util.List;

import static src.controller.vehicle.features.common.status.LogConstant.*;

@RestController
@RequestMapping("api/v1/vehicle-statuses")
@RequiredArgsConstructor
@Validated

public class VehicleStatusesController {

    private final Logger logger = LoggerFactory.getLogger(VehicleStatusesController.class);
    private final VehicleStatusService vehicleStatusService;

    @PutMapping
    public ResponseEntity<TResponse<VehicleStatusResponse>> updateStatus(
            @Valid @RequestBody UpdateVehicleStatusRequest updateVehicleStatusRequest) {
        logger.info(UPDATING_VEHICLE_STATUS, updateVehicleStatusRequest.toString());
        VehicleStatusResponse updatedStatus = this.vehicleStatusService.update(updateVehicleStatusRequest);
        logger.info(VEHICLE_STATUS_UPDATED, updatedStatus.toString());
        return new ResponseEntity<>(TResponse.<VehicleStatusResponse>tResponseBuilder()
                .response(updatedStatus)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<VehicleStatusResponse>> getById(@PathVariable int id) {
        logger.info(GETTING_VEHICLE_STATUS_DETAILS, id);
        VehicleStatusResponse status = this.vehicleStatusService.getById(id);
        logger.info(RETRIEVED_VEHICLE_STATUS_DETAILS, status.toString());
        return new ResponseEntity<>(TResponse.<VehicleStatusResponse>tResponseBuilder()
                .response(status)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/byStatus/{status}")
    public ResponseEntity<TResponse<VehicleStatusResponse>> getByStatus(
            @RequestParam(name = "status", required = false) DefaultVehicleStatus status) {
        logger.info(GETTING_VEHICLE_STATUS_BY_STATUS, status);
        VehicleStatusResponse statusResponse = this.vehicleStatusService.getByStatus(status);
        logger.info(RETRIEVED_VEHICLE_STATUS_BY_STATUS, statusResponse.toString());
        return new ResponseEntity<>(TResponse.<VehicleStatusResponse>tResponseBuilder()
                .response(statusResponse)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<VehicleStatusResponse>>> getAll() {
        logger.info(RETRIEVING_ALL_VEHICLE_STATUSES);
        List<VehicleStatusResponse> statuses = this.vehicleStatusService.getAll();
        logger.info(RETRIEVED_ALL_VEHICLE_STATUSES, statuses.size());
        return new ResponseEntity<>(TResponse.<List<VehicleStatusResponse>>tResponseBuilder()
                .response(statuses)
                .build(), HttpStatus.OK
        );
    }
}
