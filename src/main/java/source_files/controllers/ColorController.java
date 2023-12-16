package source_files.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.ColorRequests.AddColorRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.ColorRequests.UpdateColorRequest;
import source_files.data.responses.TResponse;
import source_files.services.vehicleFeaturesServices.abstracts.ColorService;

@RestController
@RequestMapping("api/color")
@AllArgsConstructor
public class ColorController {
    private ColorService colorService;


    @PostMapping("/add/color")
    public ResponseEntity<TResponse<?>> addColor(@RequestBody AddColorRequest addColorRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.colorService.add(addColorRequest))
                .message("Renk eklendi")
                .build()
        );
    }

    @PutMapping("/update/color")
    public  ResponseEntity<TResponse<?>> updateColor(@RequestBody UpdateColorRequest updateColorRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.colorService.update(updateColorRequest))
                .message("Renk güncellendi")
                .build()
        );
    }

    @GetMapping("getById/{id}")
    public  ResponseEntity<TResponse<?>> getById(@PathVariable int id) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.colorService.getById(id))
                .message(id+"li renk görüntülendi")
                .build()
        );
    }

    @GetMapping("getAll")
    public  ResponseEntity<TResponse<?>> getAll() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.colorService.getAll())
                .message("Renk Listesi döndü.")
                .build()
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TResponse<?>> delete(@PathVariable int id) {

        this.colorService.delete(id);
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .message("Renk silindi.")
                .build()
        );
    }

}
