package src.controller.vehicle.features.common.brand;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.features.common.brand.request.CreateBrandRequest;
import src.controller.vehicle.features.common.brand.request.UpdateBrandRequest;
import src.controller.vehicle.features.common.brand.response.BrandResponse;
import src.service.vehicle.features.common.brand.BrandService;

import java.util.List;

import static src.controller.vehicle.features.common.brand.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/brands")
@RequiredArgsConstructor
public class BrandController {
    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<Void> createBrand(@Valid @RequestBody CreateBrandRequest createBrandRequest) {
        log.info(CREATING_NEW_BRAND, createBrandRequest.toString());
        brandService.create(createBrandRequest);
        log.info(BRAND_SUCCESSFULLY_CREATED);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<BrandResponse>> updateBrand(@RequestBody UpdateBrandRequest updateBrandRequest) {
        log.info(UPDATING_BRAND, updateBrandRequest.toString());
        BrandResponse updatedBrand = this.brandService.update(updateBrandRequest);
        log.info(BRAND_UPDATED, updatedBrand.toString());
        return new ResponseEntity<>(TResponse.<BrandResponse>tResponseBuilder()
                .response(updatedBrand).build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<BrandResponse>> getById(@PathVariable int id) {
        log.info(GETTING_BRAND_DETAILS, id);
        BrandResponse brand = this.brandService.getById(id);
        log.info(RETRIEVED_BRAND_DETAILS, brand.toString());
        return new ResponseEntity<>(TResponse.<BrandResponse>tResponseBuilder()
                .response(brand).build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<BrandResponse>>> getAll() {
        log.info(RETRIEVING_ALL_BRANDS);
        List<BrandResponse> brands = this.brandService.getAll();
        log.info(RETRIEVED_ALL_BRANDS, brands.size());
        return new ResponseEntity<>(TResponse.<List<BrandResponse>>tResponseBuilder()
                .response(brands).build(), HttpStatus.OK);
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<BrandResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        log.info(RETRIEVING_BRANDS_BY_DELETED_STATE, isDeleted);
        List<BrandResponse> brands = this.brandService.getAllByDeletedState(isDeleted);
        log.info(RETRIEVED_BRANDS_BY_DELETED_STATE, brands.size());
        return new ResponseEntity<>(TResponse.<List<BrandResponse>>tResponseBuilder()
                .response(brands).build(), HttpStatus.OK);
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        log.info(DELETING_BRAND_WITH_ID, id, isHardDelete);
        this.brandService.delete(id, isHardDelete);
        log.info(BRAND_DELETED_SUCCESSFULLY_WITH_ID, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

