package source_files.controllers.vehicle.commonFeatures;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.controllers.vehicle.dtos.VehicleStatusDTO;
import source_files.controllers.vehicle.requests.VehicleFeaturesRequests.VehicleStatusRequests.UpdateVehicleStatusRequest;
import source_files.data.enums.defaultDataEnums.Status.DefaultVehicleStatus;
import source_files.data.responses.TResponse;
import source_files.services.vehicleFeatures.abstracts.VehicleStatusService;

import java.util.List;

@RestController
@RequestMapping("api/v1/vehicle-statuses")
@AllArgsConstructor
@Validated
@CrossOrigin
public class VehicleStatusesController {

    private final VehicleStatusService vehicleStatusService;

    @PutMapping
    public ResponseEntity<TResponse<VehicleStatusDTO>> updateStatus(
            @Valid @RequestBody UpdateVehicleStatusRequest updateVehicleStatusRequest) {
        return new ResponseEntity<>(TResponse.<VehicleStatusDTO>tResponseBuilder()
                .response(this.vehicleStatusService.update(updateVehicleStatusRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<VehicleStatusDTO>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<VehicleStatusDTO>tResponseBuilder()
                .response(this.vehicleStatusService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/byStatus/{status}")
    public ResponseEntity<TResponse<VehicleStatusDTO>> getByStatus(
            @RequestParam(name = "status", required = false) DefaultVehicleStatus status) {
        return new ResponseEntity<>(TResponse.<VehicleStatusDTO>tResponseBuilder()
                .response(this.vehicleStatusService.getByStatus(status))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<VehicleStatusDTO>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<VehicleStatusDTO>>tResponseBuilder()
                .response(this.vehicleStatusService.getAll())
                .build(), HttpStatus.OK
        );
    }
}
