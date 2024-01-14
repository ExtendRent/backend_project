package source_files.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.models.paperWorkEntities.paymentEntities.CreditCardInformation;
import source_files.data.responses.TResponse;
import source_files.services.paperWorkServices.abstracts.PaymentService;

@RestController
@RequestMapping("api/payment")
@AllArgsConstructor
public class PaymentController {

    private PaymentService paymentService;

    @PutMapping("/payWithCreditCard")
    public ResponseEntity<TResponse<?>> payWithCreditCard(@Valid @RequestParam double amount, @RequestBody CreditCardInformation creditCardInformation) throws BadRequestException {
        return ResponseEntity.ok(TResponse.tResponseBuilder()
                .response(this.paymentService.payWithCreditCard(amount, creditCardInformation))
                .message("Ödeme işlemi başarılı")
                .build()
        );
    }


}
