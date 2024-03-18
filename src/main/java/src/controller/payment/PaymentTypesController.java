package src.controller.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.payment.requests.UpdatePaymentTypeRequest;
import src.controller.payment.responses.PaymentTypeResponse;
import src.service.payment.type.PaymentTypeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/paymentTypes")
@RequiredArgsConstructor
@Validated

public class PaymentTypesController {
    private final PaymentTypeService paymentTypeService;

    @PutMapping
    public ResponseEntity<TResponse<PaymentTypeResponse>> updatePaymentType(@Valid @RequestBody UpdatePaymentTypeRequest updatePaymentTypeRequest) {
        return new ResponseEntity<>(TResponse.<PaymentTypeResponse>tResponseBuilder()
                .response(this.paymentTypeService.update(updatePaymentTypeRequest))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<PaymentTypeResponse>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<PaymentTypeResponse>tResponseBuilder()
                .response(paymentTypeService.getById(id)).build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<PaymentTypeResponse>>> GetAllPaymentTypes() {
        return new ResponseEntity<>(TResponse.<List<PaymentTypeResponse>>tResponseBuilder()
                .response(this.paymentTypeService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isActive")
    public ResponseEntity<TResponse<List<PaymentTypeResponse>>> getAllByActiveState(
            @RequestParam(value = "isActive", required = false) boolean isActive) {

        return new ResponseEntity<>(TResponse.<List<PaymentTypeResponse>>tResponseBuilder()
                .response(this.paymentTypeService.getAllByActiveState(isActive))
                .build(), HttpStatus.OK
        );
    }

}
