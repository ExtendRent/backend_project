package src.controller.payment.detail;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.payment.detail.request.UpdatePaymentDetailsRequest;
import src.controller.payment.detail.response.PaymentDetailsResponse;
import src.core.rest.BaseController;
import src.service.payment.detail.PaymentDetailsService;

import java.time.LocalDate;
import java.util.List;

import static src.controller.payment.detail.LogConstant.*;

@RestController
@Slf4j
@RequestMapping("api/v1/paymentDetails")
@RequiredArgsConstructor
public class PaymentDetailController extends BaseController {
    private final PaymentDetailsService paymentDetailsService;

    @PutMapping
    public ResponseEntity<TResponse<PaymentDetailsResponse>> update(
            @Valid @RequestBody UpdatePaymentDetailsRequest updatePaymentDetailsRequest) {
        log.info(UPDATING_PAYMENT_DETAILS, updatePaymentDetailsRequest.toString());
        PaymentDetailsResponse response = paymentDetailsService.update(updatePaymentDetailsRequest);
        log.info(PAYMENT_DETAILS_UPDATED, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<PaymentDetailsResponse>> getById(@PathVariable int id) {
        log.info(GETTING_PAYMENT_DETAILS, id);
        PaymentDetailsResponse response = paymentDetailsService.getById(id);
        log.info(RETRIEVED_PAYMENT_DETAILS, response.toString());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<PaymentDetailsResponse>>> getAll() {
        log.info(RETRIEVING_ALL_PAYMENT_DETAILS);
        List<PaymentDetailsResponse> response = paymentDetailsService.getAll();
        log.info(RETRIEVED_ALL_PAYMENT_DETAILS, response.size());
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/monthlyIncome")
    public ResponseEntity<TResponse<Double>> getMonthlyIncome(
            @RequestParam(name = "startDate", required = false) LocalDate startDate,
            @RequestParam(name = "endDate", required = false) LocalDate endDate) {
        log.info(GETTING_MONTHLY_INCOME, startDate, endDate);
        Double response = paymentDetailsService.getMonthlyIncome(startDate, endDate);
        log.info(MONTHLY_INCOME_RETRIEVED, response);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/yearlyIncome")
    public ResponseEntity<TResponse<Double>> getYearlyIncome(
            @RequestParam(name = "year", required = false) Integer year) {
        log.info(GETTING_YEARLY_INCOME, year);
        Double response = paymentDetailsService.getYearlyIncome(year);
        log.info(YEARLY_INCOME_RETRIEVED, response);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/totalIncome")
    public ResponseEntity<TResponse<Double>> getTotalIncome() {
        log.info(GETTING_TOTAL_INCOME);
        Double response = paymentDetailsService.getTotalIncome();
        log.info(TOTAL_INCOME_RETRIEVED, response);
        return answer(response, HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<TResponse<List<PaymentDetailsResponse>>> getAllFiltered(
            @RequestParam(name = "minAmount", required = false) Double minAmount,
            @RequestParam(name = "maxAmount", required = false) Double maxAmount,
            @RequestParam(name = "minDate", required = false) LocalDate minDate,
            @RequestParam(name = "maxDate", required = false) LocalDate maxDate,
            @RequestParam(name = "isDeleted", required = false) Boolean isDeleted) {
        log.info(FILTERING_PAYMENT_DETAILS, minAmount, maxAmount, minDate, maxDate, isDeleted);
        List<PaymentDetailsResponse> response = paymentDetailsService.getAllFiltered(
                minAmount, maxAmount, minDate, maxDate, isDeleted);
        log.info(PAYMENT_DETAILS_FILTERED, response.size());
        return answer(response, HttpStatus.OK);
    }
}
