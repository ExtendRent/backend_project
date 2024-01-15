package source_files.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.AddBrandRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.UpdateBrandRequest;
import source_files.data.responses.TResponse;
import source_files.services.vehicleFeaturesServices.abstracts.BrandService;

import java.util.List;

@RestController
@RequestMapping("api/brand")
@AllArgsConstructor
public class BrandController {

    private final BrandService brandService;

    @PostMapping("/add/brand")
    public ResponseEntity<TResponse<?>> addBrand(@RequestBody AddBrandRequest addBrandRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.brandService.add(addBrandRequest))
                .message("Marka eklendi")
                .build()
        );
    }

    @PutMapping("/update/brand")
    public ResponseEntity<TResponse<?>> updateBrand(@RequestBody UpdateBrandRequest updateBrandRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.brandService.update(updateBrandRequest))
                .message("Marka güncellendi")
                .build()
        );
    }

    @GetMapping("/getById")
    public ResponseEntity<TResponse<?>> getById(@RequestParam int id) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.brandService.getById(id))
                .message(id + " li marka görüntülendi")
                .build()
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<TResponse<?>> getAll() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.brandService.getAll())
                .message("Marka Listesi döndü.")
                .build()
        );
    }

    @GetMapping("/getByBrandName")
    public ResponseEntity<TResponse<?>> getByBrandName(@RequestParam String brandName) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.brandService.getByName(brandName))
                .message("Marka görüntülendi")
                .build()
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<BrandDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return ResponseEntity.ok(TResponse.<List<BrandDTO>>tResponseBuilder()
                .response(this.brandService.getAllByDeletedState(isDeleted))
                .message("Silinmeyen Araba Listesi döndü.")
                .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TResponse<?>> delete(@PathVariable int id, boolean isHardDelete) {

        this.brandService.delete(id, isHardDelete);
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .message("Marka silindi.")
                .build()
        );
    }


}
