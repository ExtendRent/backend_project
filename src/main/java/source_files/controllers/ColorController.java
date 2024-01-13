package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.AddColorRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.ColorRequests.UpdateColorRequest;
import source_files.data.responses.TResponse;
import source_files.services.vehicleFeaturesServices.abstracts.ColorService;

@RestController
@RequestMapping("api/color")
@AllArgsConstructor
public class ColorController {
    private ColorService colorService;


    @PostMapping("/add/color")
    public ResponseEntity<TResponse<?>> addColor(@RequestBody @Valid AddColorRequest addColorRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.colorService.add(addColorRequest))
                .message("Renk eklendi")
                .build()
        );
    }

    @PutMapping("/update/color")
    public ResponseEntity<TResponse<?>> updateColor(@RequestBody @Valid UpdateColorRequest updateColorRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.colorService.update(updateColorRequest))
                .message("Renk güncellendi")
                .build()
        );
    }

    @GetMapping("getById/{id}")
    public ResponseEntity<TResponse<?>> getById(@PathVariable int id) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.colorService.getById(id))
                .message(id + " id' li renk görüntülendi")
                .build()
        );
    }

    @GetMapping("getAll")
    public ResponseEntity<TResponse<?>> getAll() throws Exception {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.colorService.getAll())
                .message("Renk Listesi döndü.")
                .build()
        );
    }

    @GetMapping("/getAllByIsDeletedFalse")
    public ResponseEntity<TResponse<?>> getAllByIsDeletedFalse() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.colorService.getAllByIsDeletedFalse())
                .message("Mevcut Renk Listesi Getirildi.")
                .build()
        );
    }

    @GetMapping("/getAllByIsDeletedTrue")
    public ResponseEntity<TResponse<?>> getAllByIsDeletedTrue() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.colorService.getAllByIsDeletedTrue())
                .message("Soft Delete ile Silinen Renk Listesi Getirildi.")
                .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TResponse<?>> delete(@PathVariable int id, boolean isHardDelete) {

        this.colorService.delete(id, isHardDelete);
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .message("Renk silindi.")
                .build()
        );
    }

}
