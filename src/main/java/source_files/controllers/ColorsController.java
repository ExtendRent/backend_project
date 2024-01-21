package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.itemDTOs.ColorDTO;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.AddColorRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.UpdateColorRequest;
import source_files.data.responses.TResponse;
import source_files.services.vehicleFeaturesServices.abstracts.ColorService;

import java.util.List;

@RestController
@RequestMapping("api/v1/colors")
@AllArgsConstructor
@Validated
@CrossOrigin
public class ColorsController {
    private ColorService colorService;


    @PostMapping
    public ResponseEntity<Void> createColor(@RequestBody @Valid AddColorRequest addColorRequest) {
        this.colorService.add(addColorRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<ColorDTO>> updateColor(@RequestBody @Valid UpdateColorRequest updateColorRequest) {
        return new ResponseEntity<>(TResponse.<ColorDTO>tResponseBuilder()
                .response(this.colorService.update(updateColorRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<ColorDTO>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<ColorDTO>tResponseBuilder()
                .response(this.colorService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/")
    public ResponseEntity<TResponse<List<ColorDTO>>> getAll() throws Exception {
        return new ResponseEntity<>(TResponse.<List<ColorDTO>>tResponseBuilder()
                .response(this.colorService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<ColorDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<ColorDTO>>tResponseBuilder()
                .response(this.colorService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        this.colorService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
