package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.itemDTOs.BrandDTO;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.AddBrandRequest;
import source_files.data.requests.vehicleRequests.VehicleFeaturesRequests.BrandRequests.UpdateBrandRequest;
import source_files.data.responses.TResponse;
import source_files.services.vehicleFeaturesServices.abstracts.BrandService;

import java.util.List;

@RestController
@RequestMapping("api/v1/brands")
@AllArgsConstructor
@Validated
@CrossOrigin
public class BrandsController {

    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<HttpStatus> addBrand(@Valid @RequestBody AddBrandRequest addBrandRequest) {
        this.brandService.add(addBrandRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<BrandDTO>> updateBrand(@RequestBody UpdateBrandRequest updateBrandRequest) {
        return ResponseEntity.ok(TResponse.<BrandDTO>tResponseBuilder()
                .response(this.brandService.update(updateBrandRequest))
                .message("Marka güncellendi")
                .build()
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<TResponse<BrandDTO>> getById(@PathVariable int id) {
        return ResponseEntity.ok(TResponse.<BrandDTO>tResponseBuilder()
                .response(this.brandService.getById(id))
                .message(id + " li marka görüntülendi")
                .build()
        );
    }

    @GetMapping("/")
    public ResponseEntity<TResponse<List<BrandDTO>>> getAll() {
        return ResponseEntity.ok(TResponse.<List<BrandDTO>>tResponseBuilder()
                .response(this.brandService.getAll())
                .message("Marka Listesi döndü.")
                .build()
        );
    }

    @GetMapping("{brandName}")
    public ResponseEntity<TResponse<BrandDTO>> getByBrandName(@PathVariable String brandName) {
        return ResponseEntity.ok(TResponse.<BrandDTO>tResponseBuilder()
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
                .message("Silinme durumuna göre araba listesi döndü")
                .build()
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        this.brandService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
