package src.controllers.vehicle.common_features;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controllers.vehicle.requests.vehicleFeatures.vehicleStatus.UpdateVehicleStatusRequest;
import src.controllers.vehicle.responses.VehicleStatusResponse;
import src.data.enums.default_data_enums.status.DefaultVehicleStatus;
import src.data.global_responses.TResponse;
import src.services.vehicle_features.common_features.vehicle_status.VehicleStatusService;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicle-statuses")
@AllArgsConstructor
@Validated
@CrossOrigin
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
