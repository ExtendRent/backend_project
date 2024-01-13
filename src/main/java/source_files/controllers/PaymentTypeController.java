package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import source_files.data.requests.paperworkRequests.paymentRequests.AddPaymentTypeRequest;
import source_files.data.requests.paperworkRequests.paymentRequests.UpdatePaymentTypeRequest;
import source_files.data.responses.TResponse;
import source_files.services.paperWorkServices.abstracts.PaymentTypeService;

@RestController
@RequestMapping("api/paymentType")
@AllArgsConstructor
@Validated
public class PaymentTypeController {
    private PaymentTypeService paymentTypeService;

    @PostMapping("/add")
    public ResponseEntity<TResponse<?>> addPaymentType(@Valid @RequestBody AddPaymentTypeRequest addPaymentTypeRequest) throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.paymentTypeService.add(addPaymentTypeRequest))
                .message("Ödeme tipi ekleme işlemi başarılı")
                .build()
        );
    }

    @PutMapping("/update")
    public ResponseEntity<TResponse<?>> updatePaymentType(@Valid @RequestBody UpdatePaymentTypeRequest updatePaymentTypeRequest) throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.paymentTypeService.update(updatePaymentTypeRequest))
                .message("Ödeme tipi güncelleme işlemi başarılı")
                .build()
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<TResponse<?>> GetAllPaymentTypes() throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.paymentTypeService.getAll())
                .message("Ödeme tipleri görüntülendi")
                .build()
        );
    }

    @GetMapping("/getAllByIsDeletedFalse")
    public ResponseEntity<TResponse<?>> GetAllPaymentTypesByIsDeletedFalse() throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.paymentTypeService.getAllByIsDeletedFalse())
                .message("Ödeme tipleri görüntülendi")
                .build()
        );
    }

    @GetMapping("/getAllByIsDeletedTrue")
    public ResponseEntity<TResponse<?>> GetAllPaymentTypesByIsDeletedTrue() throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .isSuccess(true)
                .response(this.paymentTypeService.getAllByIsDeletedTrue())
                .message("Ödeme tipleri görüntülendi")
                .build()
        );
    }

}
