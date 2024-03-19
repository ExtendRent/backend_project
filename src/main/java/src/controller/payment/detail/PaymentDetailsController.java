package src.controller.payment.detail;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.payment.detail.requests.UpdatePaymentDetailsRequest;
import src.controller.payment.detail.responses.PaymentDetailsResponse;
import src.service.payment.detail.PaymentDetailsService;

import java.time.LocalDate;
import java.util.List;

import static src.controller.payment.detail.LogConstant.*;

@RestController
@RequestMapping("api/v1/paymentDetails")
@Validated
@RequiredArgsConstructor
public class PaymentDetailsController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentDetailsController.class);
    private final PaymentDetailsService paymentDetailsService;

    @PutMapping
    public ResponseEntity<TResponse<PaymentDetailsResponse>> update(
            @Valid @RequestBody UpdatePaymentDetailsRequest updatePaymentDetailsRequest) {
        logger.info(UPDATING_PAYMENT_DETAILS, updatePaymentDetailsRequest.toString());
        PaymentDetailsResponse updatedPaymentDetails = this.paymentDetailsService.update(updatePaymentDetailsRequest);
        logger.info(PAYMENT_DETAILS_UPDATED, updatedPaymentDetails.toString());
        return new ResponseEntity<>(TResponse.<PaymentDetailsResponse>tResponseBuilder()
                .response(updatedPaymentDetails).build(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<PaymentDetailsResponse>> getById(@PathVariable int id) {
        logger.info(GETTING_PAYMENT_DETAILS, id);
        PaymentDetailsResponse paymentDetails = this.paymentDetailsService.getById(id);
        logger.info(RETRIEVED_PAYMENT_DETAILS, paymentDetails.toString());
        return new ResponseEntity<>(TResponse.<PaymentDetailsResponse>tResponseBuilder()
                .response(paymentDetails).build(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<TResponse<List<PaymentDetailsResponse>>> getAll() {
        logger.info(RETRIEVING_ALL_PAYMENT_DETAILS);
        List<PaymentDetailsResponse> paymentDetailsList = this.paymentDetailsService.getAll();
        logger.info(RETRIEVED_ALL_PAYMENT_DETAILS, paymentDetailsList.size());
        return new ResponseEntity<>(TResponse.<List<PaymentDetailsResponse>>tResponseBuilder()
                .response(paymentDetailsList).build(), HttpStatus.OK);
    }

    @GetMapping("/monthlyIncome")
    public ResponseEntity<TResponse<Double>> getMonthlyIncome(
            @RequestParam(name = "startDate", required = false) LocalDate startDate,
            @RequestParam(name = "endDate", required = false) LocalDate endDate) {
        logger.info(GETTING_MONTHLY_INCOME, startDate, endDate);
        Double monthlyIncome = this.paymentDetailsService.getMonthlyIncome(startDate, endDate);
        logger.info(MONTHLY_INCOME_RETRIEVED, monthlyIncome);
        return new ResponseEntity<>(TResponse.<Double>tResponseBuilder()
                .response(monthlyIncome).build(), HttpStatus.OK);
    }

    @GetMapping("/yearlyIncome")
    public ResponseEntity<TResponse<Double>> getYearlyIncome(
            @RequestParam(name = "year", required = false) Integer year) {
        logger.info(GETTING_YEARLY_INCOME, year);
        Double yearlyIncome = this.paymentDetailsService.getYearlyIncome(year);
        logger.info(YEARLY_INCOME_RETRIEVED, yearlyIncome);
        return new ResponseEntity<>(TResponse.<Double>tResponseBuilder()
                .response(yearlyIncome).build(), HttpStatus.OK);
    }

    @GetMapping("/totalIncome")
    public ResponseEntity<TResponse<Double>> getTotalIncome() {
        logger.info(GETTING_TOTAL_INCOME);
        Double totalIncome = this.paymentDetailsService.getTotalIncome();
        logger.info(TOTAL_INCOME_RETRIEVED, totalIncome);
        return new ResponseEntity<>(TResponse.<Double>tResponseBuilder()
                .response(totalIncome).build(), HttpStatus.OK);
    }

    @GetMapping("/filter")
    public ResponseEntity<TResponse<List<PaymentDetailsResponse>>> getAllFiltered(
            @RequestParam(name = "minAmount", required = false) Double minAmount,
            @RequestParam(name = "maxAmount", required = false) Double maxAmount,
            @RequestParam(name = "minDate", required = false) LocalDate minDate,
            @RequestParam(name = "maxDate", required = false) LocalDate maxDate,
            @RequestParam(name = "isDeleted", required = false) Boolean isDeleted) {
        logger.info(FILTERING_PAYMENT_DETAILS, minAmount, maxAmount, minDate, maxDate, isDeleted);
        List<PaymentDetailsResponse> filteredPaymentDetails = this.paymentDetailsService.getAllFiltered(
                minAmount, maxAmount, minDate, maxDate, isDeleted);
        logger.info(PAYMENT_DETAILS_FILTERED, filteredPaymentDetails.size());
        return new ResponseEntity<>(TResponse.<List<PaymentDetailsResponse>>tResponseBuilder()
                .response(filteredPaymentDetails).build(), HttpStatus.OK);
    }
}
