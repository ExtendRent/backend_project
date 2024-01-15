package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
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
@RequestMapping("api/discountCode")
@AllArgsConstructor
@Validated
public class DiscountCodeController {

    private DiscountCodeService discountCodeService;

    @PostMapping("/add")
    public ResponseEntity<TResponse<DiscountCodeDTO>> addDiscountCode(@Valid @RequestBody AddDiscountCodeRequest addDiscountCodeRequest) {
        return ResponseEntity.ok(TResponse.<DiscountCodeDTO>tResponseBuilder()
                .response(this.discountCodeService.add(addDiscountCodeRequest))
                .message("Ödeme tipi ekleme işlemi başarılı")
                .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<TResponse<?>> updateDiscountCode(@Valid @RequestBody UpdateDiscountCodeRequest updateDiscountCodeRequest) {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.discountCodeService.update(updateDiscountCodeRequest))
                .message("Ödeme tipi güncelleme işlemi başarılı")
                .build()
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<TResponse<?>> getAllDiscountCodes() {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.discountCodeService.getAll())
                .message("Kiralama kayıtları görüntülendi")
                .build()
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<DiscountCodeDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {
        return ResponseEntity.ok(TResponse.<List<DiscountCodeDTO>>tResponseBuilder()
                .response(this.discountCodeService.getAllByDeletedState(isDeleted))
                .message("Silinmeyen Araba Listesi döndü.")
                .build()
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<TResponse<?>> delete(@PathVariable int id, boolean isHardDelete) {

        this.discountCodeService.delete(id, isHardDelete);
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .message("Müşteri silindi.")
                .build()
        );
    }

}
