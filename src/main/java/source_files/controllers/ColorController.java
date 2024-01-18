package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
public class ColorController {
    private ColorService colorService;


    @PostMapping
    public ResponseEntity<TResponse<HttpStatus>> addColor(@RequestBody @Valid AddColorRequest addColorRequest) {
        this.colorService.add(addColorRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<ColorDTO>> updateColor(@RequestBody @Valid UpdateColorRequest updateColorRequest) {
        return ResponseEntity.ok(TResponse.<ColorDTO>tResponseBuilder()
                .response(this.colorService.update(updateColorRequest))
                .message("Renk güncellendi")
                .build()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<TResponse<ColorDTO>> getById(@PathVariable int id) {
        return ResponseEntity.ok(TResponse.<ColorDTO>tResponseBuilder()
                .response(this.colorService.getById(id))
                .message(id + " id' li renk görüntülendi")
                .build()
        );
    }

    @GetMapping("/")
    public ResponseEntity<TResponse<List<ColorDTO>>> getAll() throws Exception {
        return ResponseEntity.ok(TResponse.<List<ColorDTO>>tResponseBuilder()
                .response(this.colorService.getAll())
                .message("Renk Listesi döndü.")
                .build()
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<ColorDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return ResponseEntity.ok(TResponse.<List<ColorDTO>>tResponseBuilder()
                .response(this.colorService.getAllByDeletedState(isDeleted))
                .message("Silinene göre renk Listesi döndü.")
                .build()
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<HttpStatus> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        this.colorService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
