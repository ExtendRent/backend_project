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
import src.core.rest.BaseController;
import src.service.payment.type.PaymentTypeService;

import java.util.List;

import static src.controller.payment.type.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/paymentTypes")
@RequiredArgsConstructor
public class PaymentTypeController extends BaseController {
    private final PaymentTypeService paymentTypeService;

    @PutMapping
    public ResponseEntity<TResponse<PaymentTypeResponse>> updatePaymentType(@Valid @RequestBody UpdatePaymentTypeRequest updatePaymentTypeRequest) {
        log.info(UPDATING_PAYMENT_TYPE, updatePaymentTypeRequest.toString());
        PaymentTypeResponse response = paymentTypeService.update(updatePaymentTypeRequest);
        log.info(PAYMENT_TYPE_UPDATED, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<PaymentTypeResponse>> getById(@PathVariable int id) {
        log.info(GETTING_PAYMENT_TYPE_DETAILS, id);
        PaymentTypeResponse response = paymentTypeService.getById(id);
        log.info(RETRIEVED_PAYMENT_TYPE_DETAILS, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<PaymentTypeResponse>>> GetAllPaymentTypes() {
        log.info(RETRIEVING_ALL_PAYMENT_TYPES);
        List<PaymentTypeResponse> response = paymentTypeService.getAll();
        log.info(RETRIEVED_ALL_PAYMENT_TYPES, response.size());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping(params = "isActive")
    public ResponseEntity<TResponse<List<PaymentTypeResponse>>> getAllByActiveState(
            @RequestParam(value = "isActive", required = false) boolean isActive) {
        log.info(RETRIEVING_PAYMENT_TYPES_BY_ACTIVE_STATE, isActive);
        List<PaymentTypeResponse> response = paymentTypeService.getAllByActiveState(isActive);
        log.info(RETRIEVED_PAYMENT_TYPES_BY_ACTIVE_STATE, response.size());
        return answer(response, HttpStatus.OK);
    }
}
