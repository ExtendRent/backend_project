package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.paperWorkDTOs.DiscountCodeDTO;
import source_files.data.requests.paperworkRequests.discountRequests.AddDiscountCodeRequest;
import source_files.data.requests.paperworkRequests.discountRequests.UpdateDiscountCodeRequest;
import source_files.data.responses.TResponse;
import source_files.services.paperWorkServices.abstracts.DiscountCodeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/discountCodes")
@AllArgsConstructor
@Validated
@CrossOrigin
public class DiscountCodesController {

    private DiscountCodeService discountCodeService;

    @PostMapping
    public ResponseEntity<Void> createDiscountCode(@Valid @RequestBody AddDiscountCodeRequest addDiscountCodeRequest) {
        this.discountCodeService.add(addDiscountCodeRequest);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<DiscountCodeDTO>>
    updateDiscountCode(@Valid @RequestBody UpdateDiscountCodeRequest updateDiscountCodeRequest) {
        return new ResponseEntity<>
                (TResponse.<DiscountCodeDTO>tResponseBuilder()
                        .response(this.discountCodeService.update(updateDiscountCodeRequest))
                        .build(), HttpStatus.OK
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<DiscountCodeDTO>> getDiscountCodeById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<DiscountCodeDTO>tResponseBuilder()
                .response(this.discountCodeService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{discountCode}")
    public ResponseEntity<TResponse<DiscountCodeDTO>> getDiscountCodeByDiscountCodeStr(@PathVariable String discountCode) {
        return new ResponseEntity<>(TResponse.<DiscountCodeDTO>tResponseBuilder()
                .response(this.discountCodeService.getByDiscountCode(discountCode))
                .build(), HttpStatus.OK
        );
    }


    @GetMapping("/")
    public ResponseEntity<TResponse<List<DiscountCodeDTO>>> getAllDiscountCodes() {
        return new ResponseEntity<>(TResponse.<List<DiscountCodeDTO>>tResponseBuilder()
                .response(this.discountCodeService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<DiscountCodeDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return new ResponseEntity<>(TResponse.<List<DiscountCodeDTO>>tResponseBuilder()
                .response(this.discountCodeService.getAllByDeletedState(isDeleted))
                .build(), HttpStatus.OK
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<Void> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        this.discountCodeService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
