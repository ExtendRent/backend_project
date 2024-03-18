package src.controller.vehicle.features.common.brand;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.vehicle.features.common.brand.requests.CreateBrandRequest;
import src.controller.vehicle.features.common.brand.requests.UpdateBrandRequest;
import src.controller.vehicle.features.common.brand.responses.BrandResponse;
import src.service.vehicle.features.common.brand.BrandService;

import java.util.List;

import static src.controller.vehicle.features.common.brand.LogConstant.*;

@RestController
@RequestMapping("api/v1/brands")
@RequiredArgsConstructor
@Validated
public class BrandsController {
    private final Logger logger = LoggerFactory.getLogger(BrandsController.class);
    private final BrandService brandService;

    @PostMapping
    public ResponseEntity<Void> createBrand(@Valid @RequestBody CreateBrandRequest createBrandRequest) {
        logger.info(CREATING_NEW_BRAND, createBrandRequest.toString());
        try {
            brandService.create(createBrandRequest);
            logger.info(BRAND_SUCCESSFULLY_CREATED);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error(ERROR_CREATING_BRAND, e);
            throw e;
        }
    }

    @PutMapping
    public ResponseEntity<TResponse<BrandResponse>> updateBrand(@RequestBody UpdateBrandRequest updateBrandRequest) {
        logger.info(UPDATING_BRAND, updateBrandRequest.toString());
        try {
            BrandResponse updatedBrand = this.brandService.update(updateBrandRequest);
            logger.info(BRAND_UPDATED, updatedBrand.toString());
            return new ResponseEntity<>(TResponse.<BrandResponse>tResponseBuilder()
                    .response(updatedBrand).build(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(ERROR_UPDATING_BRAND, e);
            throw e;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<BrandResponse>> getById(@PathVariable int id) {
        logger.info(GETTING_BRAND_DETAILS, id);
        try {
            BrandResponse brand = this.brandService.getById(id);
            logger.info(RETRIEVED_BRAND_DETAILS, brand.toString());
            return new ResponseEntity<>(TResponse.<BrandResponse>tResponseBuilder()
                    .response(brand).build(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(ERROR_GETTING_BRAND_DETAILS, e);
            throw e;
        }
    }

    @GetMapping
    public ResponseEntity<TResponse<List<BrandResponse>>> getAll() {
        logger.info(RETRIEVING_ALL_BRANDS);
        try {
            List<BrandResponse> brands = this.brandService.getAll();
            logger.info(RETRIEVED_ALL_BRANDS, brands.size());
            return new ResponseEntity<>(TResponse.<List<BrandResponse>>tResponseBuilder()
                    .response(brands).build(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(ERROR_RETRIEVING_ALL_BRANDS, e);
            throw e;
        }
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<BrandResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        logger.info(RETRIEVING_BRANDS_BY_DELETED_STATE, isDeleted);
        try {
            List<BrandResponse> brands = this.brandService.getAllByDeletedState(isDeleted);
            logger.info(RETRIEVED_BRANDS_BY_DELETED_STATE, brands.size());
            return new ResponseEntity<>(TResponse.<List<BrandResponse>>tResponseBuilder()
                    .response(brands).build(), HttpStatus.OK);
        } catch (Exception e) {
            logger.error(ERROR_RETRIEVING_BRANDS_BY_DELETED_STATE, e);
            throw e;
        }
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        logger.info(DELETING_BRAND_WITH_ID, id + " , hard delete: " + isHardDelete);
        try {
            this.brandService.delete(id, isHardDelete);
            logger.info(BRAND_DELETED_SUCCESSFULLY_WITH_ID, id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            logger.error(ERROR_DELETING_BRAND, e);
            throw e;
        }
    }
}

