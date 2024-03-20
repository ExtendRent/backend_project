package src.controller.discount;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.discount.request.CreateDiscountRequest;
import src.controller.discount.request.UpdateDiscountRequest;
import src.controller.discount.response.DiscountResponse;
import src.service.discount.DiscountService;

import java.util.List;

import static src.controller.discount.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/discounts")
@RequiredArgsConstructor
public class DiscountController {

    private final DiscountService discountService;

    @PostMapping
    public ResponseEntity<Void> createDiscountCode(@Valid @RequestBody CreateDiscountRequest createDiscountRequest) {
        log.info(CREATING_NEW_DISCOUNT_CODE, createDiscountRequest.getDiscountCode());
        this.discountService.create(createDiscountRequest);
        log.info(DISCOUNT_CODE_CREATED_SUCCESSFULLY);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<DiscountResponse>>
    updateDiscountCode(@Valid @RequestBody UpdateDiscountRequest updateDiscountRequest) {
        log.info(UPDATING_DISCOUNT_CODE, updateDiscountRequest.getId());
        TResponse<DiscountResponse> response = TResponse.<DiscountResponse>tResponseBuilder()
                .response(this.discountService.update(updateDiscountRequest))
                .build();
        log.info(DISCOUNT_CODE_UPDATED_SUCCESSFULLY, updateDiscountRequest.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<DiscountResponse>> getDiscountCodeById(@PathVariable int id) {
        log.info(GETTING_DISCOUNT_CODE_DETAILS, id);
        TResponse<DiscountResponse> response = TResponse.<DiscountResponse>tResponseBuilder()
                .response(this.discountService.getById(id))
                .build();
        log.info(DISCOUNT_CODE_DETAILS_RETRIEVED_SUCCESSFULLY, id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/code/{discountCode}")
    public ResponseEntity<TResponse<DiscountResponse>> getDiscountCodeByDiscountCodeStr(@PathVariable String discountCode) {
        log.info(GETTING_DISCOUNT_CODE_BY_CODE, discountCode);
        TResponse<DiscountResponse> response = TResponse.<DiscountResponse>tResponseBuilder()
                .response(this.discountService.getByDiscountCode(discountCode))
                .build();
        log.info(DISCOUNT_CODE_DETAILS_RETRIEVED_SUCCESSFULLY_BY_CODE, discountCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<DiscountResponse>>> getAllDiscountCodes() {
        log.info(RETRIEVING_ALL_DISCOUNT_CODES);
        TResponse<List<DiscountResponse>> response = TResponse.<List<DiscountResponse>>tResponseBuilder()
                .response(this.discountService.getAll())
                .build();
        log.info(ALL_DISCOUNT_CODES_RETRIEVED_SUCCESSFULLY);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<DiscountResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        log.info(RETRIEVING_DISCOUNT_CODES_BY_DELETED_STATE, isDeleted);
        TResponse<List<DiscountResponse>> response = TResponse.<List<DiscountResponse>>tResponseBuilder()
                .response(this.discountService.getAllByDeletedState(isDeleted))
                .build();
        log.info(DISCOUNT_CODES_RETRIEVED_SUCCESSFULLY_BY_DELETED_STATE, isDeleted);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(params = "isActive")
    public ResponseEntity<TResponse<List<DiscountResponse>>> getAllByActiveState(
            @RequestParam(value = "isActive", required = false) boolean isActive) {
        log.info(RETRIEVING_DISCOUNT_CODES_BY_ACTIVE_STATE, isActive);
        TResponse<List<DiscountResponse>> response = TResponse.<List<DiscountResponse>>tResponseBuilder()
                .response(this.discountService.getAllByActiveState(isActive))
                .build();
        log.info(DISCOUNT_CODES_RETRIEVED_SUCCESSFULLY_BY_ACTIVE_STATE, isActive);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {
        log.info(DELETING_DISCOUNT_CODE, id, isHardDelete);
        this.discountService.delete(id, isHardDelete);
        log.info(DISCOUNT_CODE_DELETED_SUCCESSFULLY, id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
