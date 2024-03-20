package src.controller.vehicle.features.common.color;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.features.common.color.request.CreateColorRequest;
import src.controller.vehicle.features.common.color.request.UpdateColorRequest;
import src.controller.vehicle.features.common.color.response.ColorResponse;
import src.service.vehicle.features.common.color.ColorService;

import java.util.List;

import static src.controller.vehicle.features.common.color.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/colors")
@RequiredArgsConstructor
public class ColorController {
    private final ColorService colorService;

    @PostMapping
    public ResponseEntity<Void> createColor(@Valid @RequestBody CreateColorRequest createColorRequest) {
        log.info(CREATING_NEW_COLOR, createColorRequest.toString());
        colorService.create(createColorRequest);
        log.info(COLOR_SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<ColorResponse>> updateColor(@RequestBody UpdateColorRequest updateColorRequest) {
        log.info(UPDATING_COLOR, updateColorRequest.toString());
        ColorResponse updatedColor = this.colorService.update(updateColorRequest);
        log.info(COLOR_UPDATED, updatedColor.toString());
        return new ResponseEntity<>(TResponse.<ColorResponse>tResponseBuilder()
                .response(updatedColor).build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<ColorResponse>> getById(@PathVariable int id) {
        log.info(GETTING_COLOR_DETAILS, id);
        ColorResponse color = this.colorService.getById(id);
        log.info(RETRIEVED_COLOR_DETAILS, color.toString());
        return new ResponseEntity<>(TResponse.<ColorResponse>tResponseBuilder()
                .response(color).build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<ColorResponse>>> getAll() {
        log.info(RETRIEVING_ALL_COLORS);
        List<ColorResponse> colors = this.colorService.getAll();
        log.info(RETRIEVED_ALL_COLORS, colors.size());
        return new ResponseEntity<>(TResponse.<List<ColorResponse>>tResponseBuilder()
                .response(colors).build(), HttpStatus.OK);
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<ColorResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        log.info(RETRIEVING_COLORS_BY_DELETED_STATE, isDeleted);
        List<ColorResponse> colors = this.colorService.getAllByDeletedState(isDeleted);
        log.info(RETRIEVED_COLORS_BY_DELETED_STATE, colors.size());
        return new ResponseEntity<>(TResponse.<List<ColorResponse>>tResponseBuilder()
                .response(colors).build(), HttpStatus.OK);
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        log.info(DELETING_COLOR_WITH_ID, id, isHardDelete);
        this.colorService.delete(id, isHardDelete);
        log.info(COLOR_DELETED_SUCCESSFULLY_WITH_ID, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
