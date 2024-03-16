package src.controllers.paperwork;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controllers.paperwork.requests.discount.CreateDiscountRequest;
import src.controllers.paperwork.requests.discount.UpdateDiscountRequest;
import src.controllers.paperwork.responses.DiscountResponse;
import src.data.global_responses.TResponse;
import src.services.paperwork.discount.DiscountService;

import java.util.List;

@RestController
@RequestMapping("api/v1/discounts")
@AllArgsConstructor
@Validated
@CrossOrigin
public class DiscountsController {

    private DiscountService discountService;

    @PostMapping
    public ResponseEntity<Void> createDiscountCode(@Valid @RequestBody CreateDiscountRequest createDiscountRequest) {
        this.discountService.create(createDiscountRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<DiscountResponse>>
    updateDiscountCode(@Valid @RequestBody UpdateDiscountRequest updateDiscountRequest) {
        return new ResponseEntity<>
                (TResponse.<DiscountResponse>tResponseBuilder()
                        .response(this.discountService.update(updateDiscountRequest))
                        .build(), HttpStatus.OK
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<DiscountResponse>> getDiscountCodeById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<DiscountResponse>tResponseBuilder()
                .response(this.discountService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/code/{discountCode}")
    public ResponseEntity<TResponse<DiscountResponse>> getDiscountCodeByDiscountCodeStr(@PathVariable String discountCode) {
        return new ResponseEntity<>(TResponse.<DiscountResponse>tResponseBuilder()
                .response(this.discountService.getByDiscountCode(discountCode))
                .build(), HttpStatus.OK
        );
    }


    @GetMapping
    public ResponseEntity<TResponse<List<DiscountResponse>>> getAllDiscountCodes() {
        return new ResponseEntity<>(TResponse.<List<DiscountResponse>>tResponseBuilder()
                .response(this.discountService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<DiscountResponse>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<DiscountResponse>>tResponseBuilder()
                .response(this.discountService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isActive")
    public ResponseEntity<TResponse<List<DiscountResponse>>> getAllByActiveState(
            @RequestParam(value = "isActive", required = false) boolean isActive) {
        return new ResponseEntity<>(TResponse.<List<DiscountResponse>>tResponseBuilder()
                .response(this.discountService.getAllByActiveState(isActive))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        this.discountService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
