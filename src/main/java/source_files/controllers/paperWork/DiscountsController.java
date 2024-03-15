package source_files.controllers.paperWork;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.controllers.paperWork.dtos.DiscountDTO;
import source_files.controllers.paperWork.requests.discount.CreateDiscountRequest;
import source_files.controllers.paperWork.requests.discount.UpdateDiscountRequest;
import source_files.data.responses.TResponse;
import source_files.services.paperWork.abstracts.DiscountService;

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
    public ResponseEntity<TResponse<DiscountDTO>>
    updateDiscountCode(@Valid @RequestBody UpdateDiscountRequest updateDiscountRequest) {
        return new ResponseEntity<>
                (TResponse.<DiscountDTO>tResponseBuilder()
                        .response(this.discountService.update(updateDiscountRequest))
                        .build(), HttpStatus.OK
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<DiscountDTO>> getDiscountCodeById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<DiscountDTO>tResponseBuilder()
                .response(this.discountService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/code/{discountCode}")
    public ResponseEntity<TResponse<DiscountDTO>> getDiscountCodeByDiscountCodeStr(@PathVariable String discountCode) {
        return new ResponseEntity<>(TResponse.<DiscountDTO>tResponseBuilder()
                .response(this.discountService.getByDiscountCode(discountCode))
                .build(), HttpStatus.OK
        );
    }


    @GetMapping
    public ResponseEntity<TResponse<List<DiscountDTO>>> getAllDiscountCodes() {
        return new ResponseEntity<>(TResponse.<List<DiscountDTO>>tResponseBuilder()
                .response(this.discountService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<DiscountDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<DiscountDTO>>tResponseBuilder()
                .response(this.discountService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isActive")
    public ResponseEntity<TResponse<List<DiscountDTO>>> getAllByActiveState(
            @RequestParam(value = "isActive", required = false) boolean isActive) {
        return new ResponseEntity<>(TResponse.<List<DiscountDTO>>tResponseBuilder()
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
