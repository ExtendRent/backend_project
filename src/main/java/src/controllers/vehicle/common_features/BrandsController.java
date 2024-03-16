package src.controllers.vehicle.common_features;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controllers.vehicle.requests.vehicleFeatures.brand.CreateBrandRequest;
import src.controllers.vehicle.requests.vehicleFeatures.brand.UpdateBrandRequest;
import src.controllers.vehicle.responses.BrandResponse;
import src.data.global_responses.TResponse;
import src.services.vehicle_features.common_features.brand.BrandService;

import java.util.List;

@RestController
@RequestMapping("api/v1/brands")
@AllArgsConstructor
@Validated
@CrossOrigin
public class BrandsController {

    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<Void> createBrand(@Valid @RequestBody CreateBrandRequest createBrandRequest) {
        this.brandService.create(createBrandRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<BrandResponse>> updateBrand(@RequestBody UpdateBrandRequest updateBrandRequest) {
        return new ResponseEntity<>(TResponse.<BrandResponse>tResponseBuilder()
                .response(this.brandService.update(updateBrandRequest)).build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<BrandResponse>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<BrandResponse>tResponseBuilder()
                .response(this.brandService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<BrandResponse>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<BrandResponse>>tResponseBuilder()
                .response(this.brandService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<BrandResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<BrandResponse>>tResponseBuilder()
                .response(this.brandService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        this.brandService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
