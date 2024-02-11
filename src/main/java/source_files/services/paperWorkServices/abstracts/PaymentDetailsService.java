package source_files.services.paperWorkServices.abstracts;

import source_files.data.DTO.paperWorkDTOs.PaymentDetailsDTO;
import source_files.data.requests.paperworkRequests.paymentRequests.UpdatePaymentDetailsRequest;

import java.time.LocalDate;
import java.util.List;

public interface PaymentDetailsService {

    PaymentDetailsDTO update(UpdatePaymentDetailsRequest updatePaymentDetailsRequest);

    PaymentDetailsDTO getById(int id);

    List<PaymentDetailsDTO> getAll();

    List<PaymentDetailsDTO> getAllFiltered(Double minAmount, Double maxAmount,
                                           LocalDate minDate, LocalDate maxDate, Boolean isDeleted);

    Double getMonthlyIncome(LocalDate startDate, LocalDate endDate);

    Double getYearlyIncome(int year);

    Double getTotalIncome();

}
