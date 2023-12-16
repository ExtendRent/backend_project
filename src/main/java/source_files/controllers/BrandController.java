package source_files.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.BrandRequests.AddBrandRequest;
import source_files.data.requests.itemRequests.VehicleFeaturesRequests.BrandRequests.UpdateBrandRequest;
import source_files.data.responses.TResponse;
import source_files.services.vehicleFeaturesServices.abstracts.BrandService;

@RestController
@RequestMapping("api/brand")
@AllArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping("/add/brand")
    public ResponseEntity<TResponse<?>> addBrand(@RequestBody AddBrandRequest addBrandRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.brandService.add(addBrandRequest))
                .message("Marka eklendi")
                .build()
        );
    }

    @PutMapping("/update/brand")
    public  ResponseEntity<TResponse<?>> updateBrand(@RequestBody UpdateBrandRequest updateBrandRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.brandService.update(updateBrandRequest))
                .message("Marka güncellendi")
                .build()
        );
    }

    @GetMapping("getById/{id}")
    public  ResponseEntity<TResponse<?>> getById(@PathVariable int id) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.brandService.getById(id))
                .message(id+"li marka görüntülendi")
                .build()
        );
    }

    @GetMapping("getAll")
    public  ResponseEntity<TResponse<?>> getAll() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.brandService.getAll())
                .message("Marka Listesi döndü.")
                .build()
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TResponse<?>> delete(@PathVariable int id) {

        this.brandService.delete(id);
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .message("Marka silindi.")
                .build()
        );
    }


}
