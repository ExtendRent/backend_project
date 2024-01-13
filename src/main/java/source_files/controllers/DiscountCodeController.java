package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.data.requests.paperworkRequests.discountRequests.AddDiscountCodeRequest;
import source_files.data.requests.paperworkRequests.discountRequests.UpdateDiscountCodeRequest;
import source_files.data.responses.TResponse;
import source_files.services.paperWorkServices.abstracts.DiscountCodeService;

@RestController
@RequestMapping("api/discountCode")
@AllArgsConstructor
@Validated
public class DiscountCodeController {

    private DiscountCodeService discountCodeService;

    @PostMapping("/add")
    public ResponseEntity<TResponse<?>> addDiscountCode(@Valid @RequestBody AddDiscountCodeRequest addDiscountCodeRequest) throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.discountCodeService.add(addDiscountCodeRequest))
                .message("Ödeme tipi ekleme işlemi başarılı")
                .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<TResponse<?>> updateDiscountCode(@Valid @RequestBody UpdateDiscountCodeRequest updateDiscountCodeRequest) throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.discountCodeService.update(updateDiscountCodeRequest))
                .message("Ödeme tipi güncelleme işlemi başarılı")
                .build()
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<TResponse<?>> getAllDiscountCodes() throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.discountCodeService.getAll())
                .message("Kiralama kayıtları görüntülendi")
                .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TResponse<?>> delete(@PathVariable int id, boolean isHardDelete) {

        this.discountCodeService.delete(id, isHardDelete);
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .message("Müşteri silindi.")
                .build()
        );
    }

}
