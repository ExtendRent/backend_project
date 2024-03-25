package src.controller.vehicle.features.common.status;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.features.common.status.request.UpdateVehicleStatusRequest;
import src.controller.vehicle.features.common.status.response.VehicleStatusResponse;
import src.core.rest.BaseController;
import src.service.vehicle.features.common.status.VehicleStatusService;
import src.service.vehicle.features.common.status.model.DefaultVehicleStatus;

import java.util.List;

import static src.controller.vehicle.features.common.status.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/vehicle-statuses")
@RequiredArgsConstructor
public class VehicleStatusController extends BaseController {
    private final VehicleStatusService vehicleStatusService;

    @PutMapping
    public ResponseEntity<TResponse<VehicleStatusResponse>> updateStatus(
            @Valid @RequestBody UpdateVehicleStatusRequest updateVehicleStatusRequest) {
        log.info(UPDATING_VEHICLE_STATUS, updateVehicleStatusRequest.toString());
        VehicleStatusResponse response = vehicleStatusService.update(updateVehicleStatusRequest);
        log.info(VEHICLE_STATUS_UPDATED, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<VehicleStatusResponse>> getById(@PathVariable int id) {
        log.info(GETTING_VEHICLE_STATUS_DETAILS, id);
        VehicleStatusResponse response = vehicleStatusService.getById(id);
        log.info(RETRIEVED_VEHICLE_STATUS_DETAILS, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/byStatus/{status}")
    public ResponseEntity<TResponse<VehicleStatusResponse>> getByStatus(
            @RequestParam(name = "status", required = false) DefaultVehicleStatus status) {
        log.info(GETTING_VEHICLE_STATUS_BY_STATUS, status);
        VehicleStatusResponse response = vehicleStatusService.getByStatus(status);
        log.info(RETRIEVED_VEHICLE_STATUS_BY_STATUS, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<VehicleStatusResponse>>> getAll() {
        log.info(RETRIEVING_ALL_VEHICLE_STATUSES);
        List<VehicleStatusResponse> response = vehicleStatusService.getAll();
        log.info(RETRIEVED_ALL_VEHICLE_STATUSES, response.size());
        return answer(response, HttpStatus.OK);
    }
}
