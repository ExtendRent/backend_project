package src.controller.vehicle.features.common.status;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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

@RestController
@RequestMapping("api/v1/vehicle-statuses")
@RequiredArgsConstructor
@Validated

public class VehicleStatusesController {

    private final VehicleStatusService vehicleStatusService;

    @PutMapping
    public ResponseEntity<TResponse<VehicleStatusResponse>> updateStatus(
            @Valid @RequestBody UpdateVehicleStatusRequest updateVehicleStatusRequest) {
        return new ResponseEntity<>(TResponse.<VehicleStatusResponse>tResponseBuilder()
                .response(this.vehicleStatusService.update(updateVehicleStatusRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<VehicleStatusResponse>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<VehicleStatusResponse>tResponseBuilder()
                .response(this.vehicleStatusService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/byStatus/{status}")
    public ResponseEntity<TResponse<VehicleStatusResponse>> getByStatus(
            @RequestParam(name = "status", required = false) DefaultVehicleStatus status) {
        return new ResponseEntity<>(TResponse.<VehicleStatusResponse>tResponseBuilder()
                .response(this.vehicleStatusService.getByStatus(status))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<VehicleStatusResponse>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<VehicleStatusResponse>>tResponseBuilder()
                .response(this.vehicleStatusService.getAll())
                .build(), HttpStatus.OK
        );
    }
}
