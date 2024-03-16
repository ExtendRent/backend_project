package src.controllers.paperwork;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controllers.paperwork.requests.payment.UpdatePaymentTypeRequest;
import src.controllers.paperwork.responses.PaymentTypeResponse;
import src.data.global_responses.TResponse;
import src.services.paperwork.payment_type.PaymentTypeService;

import java.util.List;

@RestController
@RequestMapping("api/v1/paymentTypes")
@AllArgsConstructor
@Validated
@CrossOrigin
public class PaymentTypesController {
    private PaymentTypeService paymentTypeService;

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
