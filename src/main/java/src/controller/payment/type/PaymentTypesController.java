package src.controller.payment.type;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.payment.type.requests.UpdatePaymentTypeRequest;
import src.controller.payment.type.responses.PaymentTypeResponse;
import src.service.payment.type.PaymentTypeService;

import java.util.List;

import static src.controller.payment.type.LogConstant.*;

@RestController
@RequestMapping("api/v1/paymentTypes")
@RequiredArgsConstructor
@Validated
public class PaymentTypesController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentTypesController.class);
    private final PaymentTypeService paymentTypeService;

    @PutMapping
    public ResponseEntity<TResponse<PaymentTypeResponse>> updatePaymentType(@Valid @RequestBody UpdatePaymentTypeRequest updatePaymentTypeRequest) {
        logger.info(UPDATING_PAYMENT_TYPE, updatePaymentTypeRequest.toString());
        PaymentTypeResponse updatedPaymentType = this.paymentTypeService.update(updatePaymentTypeRequest);
        logger.info(PAYMENT_TYPE_UPDATED, updatedPaymentType.toString());
        return new ResponseEntity<>(TResponse.<PaymentTypeResponse>tResponseBuilder()
                .response(updatedPaymentType)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<PaymentTypeResponse>> getById(@PathVariable int id) {
        logger.info(GETTING_PAYMENT_TYPE_DETAILS, id);
        PaymentTypeResponse paymentType = this.paymentTypeService.getById(id);
        logger.info(RETRIEVED_PAYMENT_TYPE_DETAILS, paymentType.toString());
        return new ResponseEntity<>(TResponse.<PaymentTypeResponse>tResponseBuilder()
                .response(paymentType)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<PaymentTypeResponse>>> GetAllPaymentTypes() {
        logger.info(RETRIEVING_ALL_PAYMENT_TYPES);
        List<PaymentTypeResponse> paymentTypes = this.paymentTypeService.getAll();
        logger.info(RETRIEVED_ALL_PAYMENT_TYPES, paymentTypes.size());
        return new ResponseEntity<>(TResponse.<List<PaymentTypeResponse>>tResponseBuilder()
                .response(paymentTypes)
                .build(), HttpStatus.OK
        );
    }

    @GetMapping(params = "isActive")
    public ResponseEntity<TResponse<List<PaymentTypeResponse>>> getAllByActiveState(
            @RequestParam(value = "isActive", required = false) boolean isActive) {
        logger.info(RETRIEVING_PAYMENT_TYPES_BY_ACTIVE_STATE, isActive);
        List<PaymentTypeResponse> paymentTypes = this.paymentTypeService.getAllByActiveState(isActive);
        logger.info(RETRIEVED_PAYMENT_TYPES_BY_ACTIVE_STATE, paymentTypes.size());
        return new ResponseEntity<>(TResponse.<List<PaymentTypeResponse>>tResponseBuilder()
                .response(paymentTypes)
                .build(), HttpStatus.OK
        );
    }
}
