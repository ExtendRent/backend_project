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
import src.core.rest.BaseController;
import src.service.vehicle.features.common.color.ColorService;

import java.util.List;

import static src.controller.vehicle.features.common.color.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/colors")
@RequiredArgsConstructor
public class ColorController extends BaseController {
    private final ColorService colorService;

    @PostMapping
    public ResponseEntity<Void> createColor(@Valid @RequestBody CreateColorRequest createColorRequest) {
        log.info(CREATING_NEW_COLOR, createColorRequest.toString());
        colorService.create(createColorRequest);
        log.info(COLOR_SUCCESSFULLY_CREATED);
        return answer(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<ColorResponse>> updateColor(@RequestBody UpdateColorRequest updateColorRequest) {
        log.info(UPDATING_COLOR, updateColorRequest.toString());
        ColorResponse response = colorService.update(updateColorRequest);
        log.info(COLOR_UPDATED, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<ColorResponse>> getById(@PathVariable int id) {
        log.info(GETTING_COLOR_DETAILS, id);
        ColorResponse response = colorService.getById(id);
        log.info(RETRIEVED_COLOR_DETAILS, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<ColorResponse>>> getAll() {
        log.info(RETRIEVING_ALL_COLORS);
        List<ColorResponse> response = colorService.getAll();
        log.info(RETRIEVED_ALL_COLORS, response.size());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<ColorResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        log.info(RETRIEVING_COLORS_BY_DELETED_STATE, isDeleted);
        List<ColorResponse> response = colorService.getAllByDeletedState(isDeleted);
        log.info(RETRIEVED_COLORS_BY_DELETED_STATE, response.size());
        return answer(response, HttpStatus.OK);
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        log.info(DELETING_COLOR_WITH_ID, id, isHardDelete);
        colorService.delete(id, isHardDelete);
        log.info(COLOR_DELETED_SUCCESSFULLY_WITH_ID, id);
        return answer(HttpStatus.NO_CONTENT);
    }
}
