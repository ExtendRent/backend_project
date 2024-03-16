package src.services.paperwork.payment_details;

import src.controllers.paperwork.requests.payment.UpdatePaymentDetailsRequest;
import src.controllers.paperwork.responses.PaymentDetailsResponse;

import java.time.LocalDate;
import java.util.List;

public interface PaymentDetailsService {

    PaymentDetailsResponse update(UpdatePaymentDetailsRequest updatePaymentDetailsRequest);

    PaymentDetailsResponse getById(int id);

    List<PaymentDetailsResponse> getAll();

    List<PaymentDetailsResponse> getAllFiltered(Double minAmount, Double maxAmount,
                                                LocalDate minDate, LocalDate maxDate, Boolean isDeleted);

    Double getMonthlyIncome(LocalDate startDate, LocalDate endDate);

    Double getYearlyIncome(int year);

    Double getTotalIncome();

}
