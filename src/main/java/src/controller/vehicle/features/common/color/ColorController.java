package src.controller.vehicle.features.common.color;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("api/v1/colors")
@RequiredArgsConstructor

public class ColorController {
    private static final Logger logger = LoggerFactory.getLogger(ColorController.class);
    private final ColorService colorService;

    @PostMapping
    public ResponseEntity<Void> createColor(@Valid @RequestBody CreateColorRequest createColorRequest) {
        logger.info(CREATING_NEW_COLOR, createColorRequest.toString());
        colorService.create(createColorRequest);
        logger.info(COLOR_SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<ColorResponse>> updateColor(@RequestBody UpdateColorRequest updateColorRequest) {
        logger.info(UPDATING_COLOR, updateColorRequest.toString());
        ColorResponse updatedColor = this.colorService.update(updateColorRequest);
        logger.info(COLOR_UPDATED, updatedColor.toString());
        return new ResponseEntity<>(TResponse.<ColorResponse>tResponseBuilder()
                .response(updatedColor).build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<ColorResponse>> getById(@PathVariable int id) {
        logger.info(GETTING_COLOR_DETAILS, id);
        ColorResponse color = this.colorService.getById(id);
        logger.info(RETRIEVED_COLOR_DETAILS, color.toString());
        return new ResponseEntity<>(TResponse.<ColorResponse>tResponseBuilder()
                .response(color).build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<ColorResponse>>> getAll() {
        logger.info(RETRIEVING_ALL_COLORS);
        List<ColorResponse> colors = this.colorService.getAll();
        logger.info(RETRIEVED_ALL_COLORS, colors.size());
        return new ResponseEntity<>(TResponse.<List<ColorResponse>>tResponseBuilder()
                .response(colors).build(), HttpStatus.OK);
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<ColorResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        logger.info(RETRIEVING_COLORS_BY_DELETED_STATE, isDeleted);
        List<ColorResponse> colors = this.colorService.getAllByDeletedState(isDeleted);
        logger.info(RETRIEVED_COLORS_BY_DELETED_STATE, colors.size());
        return new ResponseEntity<>(TResponse.<List<ColorResponse>>tResponseBuilder()
                .response(colors).build(), HttpStatus.OK);
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        logger.info(DELETING_COLOR_WITH_ID, id, isHardDelete);
        this.colorService.delete(id, isHardDelete);
        logger.info(COLOR_DELETED_SUCCESSFULLY_WITH_ID, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
