package src.controller.payment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.payment.requests.UpdatePaymentDetailsRequest;
import src.controller.payment.responses.PaymentDetailsResponse;
import src.service.payment.detail.PaymentDetailsService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("api/v1/paymentDetails")
@Validated
@RequiredArgsConstructor
public class PaymentDetailsController {
    private final PaymentDetailsService paymentDetailsService;

    @PutMapping
    public ResponseEntity<TResponse<PaymentDetailsResponse>> update(
            @Valid @RequestBody UpdatePaymentDetailsRequest updatePaymentDetailsRequest) {
        return new ResponseEntity<>
                (TResponse.<PaymentDetailsResponse>tResponseBuilder()
                        .response(this.paymentDetailsService.update(updatePaymentDetailsRequest))
                        .build(), HttpStatus.OK
                );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TResponse<PaymentDetailsResponse>> getById(@PathVariable int id) {
        return new ResponseEntity<>(TResponse.<PaymentDetailsResponse>tResponseBuilder()
                .response(this.paymentDetailsService.getById(id))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping
    public ResponseEntity<TResponse<List<PaymentDetailsResponse>>> getAll() {
        return new ResponseEntity<>(TResponse.<List<PaymentDetailsResponse>>tResponseBuilder()
                .response(this.paymentDetailsService.getAll())
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/monthlyIncome")
    public ResponseEntity<TResponse<Double>> getMonthlyIncome(
            @RequestParam(name = "startDate", required = false) LocalDate startDate,
            @RequestParam(name = "endDate", required = false) LocalDate endDate) {
        return new ResponseEntity<>(TResponse.<Double>tResponseBuilder()
                .response(this.paymentDetailsService.getMonthlyIncome(startDate, endDate))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/yearlyIncome")
    public ResponseEntity<TResponse<Double>> getYearlyIncome(
            @RequestParam(name = "year", required = false) Integer year) {
        return new ResponseEntity<>(TResponse.<Double>tResponseBuilder()
                .response(this.paymentDetailsService.getYearlyIncome(year))
                .build(), HttpStatus.OK
        );
    }

    @GetMapping("/totalIncome")
    public ResponseEntity<TResponse<Double>> getTotalIncome() {
        return new ResponseEntity<>(TResponse.<Double>tResponseBuilder()
                .response(this.paymentDetailsService.getTotalIncome())
                .build(), HttpStatus.OK
        );
    }


    @GetMapping("/filter")
    public ResponseEntity<TResponse<List<PaymentDetailsResponse>>> getALlFiltered(

            @RequestParam(name = "minAmount", required = false) Double minAmount,
            @RequestParam(name = "maxAmount", required = false) Double maxAmount,
            @RequestParam(name = "minDate", required = false) LocalDate minDate,
            @RequestParam(name = "maxDate", required = false) LocalDate maxDate,
            @RequestParam(name = "isDeleted", required = false) Boolean isDeleted) {

        return new ResponseEntity<>(TResponse.<List<PaymentDetailsResponse>>tResponseBuilder()
                .response(this.paymentDetailsService.getAllFiltered(
                        minAmount, maxAmount, minDate, maxDate, isDeleted)
                )
                .build(), HttpStatus.OK
        );
    }

}
