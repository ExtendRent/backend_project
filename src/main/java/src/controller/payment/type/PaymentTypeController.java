package src.controller.payment.type;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.payment.type.request.UpdatePaymentTypeRequest;
import src.controller.payment.type.response.PaymentTypeResponse;
import src.service.payment.type.PaymentTypeService;

import java.util.List;

import static src.controller.payment.type.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/paymentTypes")
@RequiredArgsConstructor
public class PaymentTypeController {
    private final PaymentTypeService paymentTypeService;

    @PutMapping
    public ResponseEntity<TResponse<PaymentTypeResponse>> updatePaymentType(@Valid @RequestBody UpdatePaymentTypeRequest updatePaymentTypeRequest) {
        log.info(UPDATING_PAYMENT_TYPE, updatePaymentTypeRequest.toString());
        PaymentTypeResponse updatedPaymentType = this.paymentTypeService.update(updatePaymentTypeRequest);
        log.info(PAYMENT_TYPE_UPDATED, updatedPaymentType.toString());
        return new ResponseEntity<>(TResponse.<PaymentTypeResponse>tResponseBuilder()
                .response(updatedPaymentType)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<PaymentTypeResponse>> getById(@PathVariable int id) {
        log.info(GETTING_PAYMENT_TYPE_DETAILS, id);
        PaymentTypeResponse paymentType = this.paymentTypeService.getById(id);
        log.info(RETRIEVED_PAYMENT_TYPE_DETAILS, paymentType.toString());
        return new ResponseEntity<>(TResponse.<PaymentTypeResponse>tResponseBuilder()
                .response(paymentType)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<PaymentTypeResponse>>> GetAllPaymentTypes() {
        log.info(RETRIEVING_ALL_PAYMENT_TYPES);
        List<PaymentTypeResponse> paymentTypes = this.paymentTypeService.getAll();
        log.info(RETRIEVED_ALL_PAYMENT_TYPES, paymentTypes.size());
        return new ResponseEntity<>(TResponse.<List<PaymentTypeResponse>>tResponseBuilder()
                .response(paymentTypes)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isActive")
    public ResponseEntity<TResponse<List<PaymentTypeResponse>>> getAllByActiveState(
            @RequestParam(value = "isActive", required = false) boolean isActive) {
        log.info(RETRIEVING_PAYMENT_TYPES_BY_ACTIVE_STATE, isActive);
        List<PaymentTypeResponse> paymentTypes = this.paymentTypeService.getAllByActiveState(isActive);
        log.info(RETRIEVED_PAYMENT_TYPES_BY_ACTIVE_STATE, paymentTypes.size());
        return new ResponseEntity<>(TResponse.<List<PaymentTypeResponse>>tResponseBuilder()
                .response(paymentTypes)
                .build(), HttpStatus.OK
        );
    }
}
