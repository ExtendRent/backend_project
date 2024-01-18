package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.data.DTO.paperWorkDTOs.PaymentTypeDTO;
import source_files.data.requests.paperworkRequests.paymentRequests.AddPaymentTypeRequest;
import source_files.data.requests.paperworkRequests.paymentRequests.UpdatePaymentTypeRequest;
import source_files.data.responses.TResponse;
import source_files.services.paperWorkServices.abstracts.PaymentTypeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/paymentTypes")
@AllArgsConstructor
@Validated
@CrossOrigin
public class PaymentTypesController {
    private PaymentTypeService paymentTypeService;

    @PostMapping
    public ResponseEntity<TResponse<HttpStatus>> addPaymentType(@Valid @RequestBody AddPaymentTypeRequest addPaymentTypeRequest) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<TResponse<PaymentTypeDTO>> updatePaymentType(@Valid @RequestBody UpdatePaymentTypeRequest updatePaymentTypeRequest) {
        return ResponseEntity.ok(TResponse.<PaymentTypeDTO>tResponseBuilder()
                .response(this.paymentTypeService.update(updatePaymentTypeRequest))
                .message("Ödeme tipi güncelleme işlemi başarılı")
                .build()
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<PaymentTypeDTO>>> GetAllPaymentTypes() {
        return ResponseEntity.ok(TResponse.<List<PaymentTypeDTO>>tResponseBuilder()
                .response(this.paymentTypeService.getAll())
                .message("Ödeme tipleri görüntülendi")
                .build()
        );
    }

    @GetMapping(params = "isDeleted")
    public ResponseEntity<TResponse<List<PaymentTypeDTO>>> getAllByDeletedState(
            @RequestParam(value = "isDeleted", required = false) boolean isDeleted) {

        return ResponseEntity.ok(TResponse.<List<PaymentTypeDTO>>tResponseBuilder()
                .response(this.paymentTypeService.getAllByDeletedState(isDeleted))
                .message("Araba Listesi döndü.")
                .build()
        );
    }

    @DeleteMapping(params = {"id", "isHardDelete"})
    public ResponseEntity<HttpStatus> delete(
            @RequestParam(name = "id") int id, @RequestParam(value = "isHardDelete") boolean isHardDelete) {

        this.paymentTypeService.delete(id, isHardDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
