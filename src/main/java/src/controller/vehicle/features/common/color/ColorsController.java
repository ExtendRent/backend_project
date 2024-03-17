package src.controller.vehicle.features.common.color;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.features.common.color.requests.CreateColorRequest;
import src.controller.vehicle.features.common.color.requests.UpdateColorRequest;
import src.controller.vehicle.features.common.color.responses.ColorResponse;
import src.service.vehicle.features.common.color.ColorService;

import java.util.List;

@RestController
@RequestMapping("api/v1/colors")
@AllArgsConstructor
@Validated

public class ColorsController {
    private final ColorService colorService;

    @PostMapping
    public ResponseEntity<Void> createColor(@RequestBody @Valid CreateColorRequest createColorRequest) {
        this.colorService.create(createColorRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<ColorResponse>> updateColor(@RequestBody @Valid UpdateColorRequest updateColorRequest) {
        return new ResponseEntity<>(TResponse.<ColorResponse>tResponseBuilder()
                .response(this.colorService.update(updateColorRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<ColorResponse>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<ColorResponse>tResponseBuilder()
                .response(this.colorService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<ColorResponse>>> getAll() throws Exception {
        return new ResponseEntity<>(TResponse.<List<ColorResponse>>tResponseBuilder()
                .response(this.colorService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<ColorResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<ColorResponse>>tResponseBuilder()
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
