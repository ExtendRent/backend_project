package src.repository.payment.detail;

import src.controller.payment.detail.request.UpdatePaymentDetailsRequest;

import java.util.List;

public interface PaymentDetailsEntityService {

    PaymentDetailsEntity create(PaymentDetailsEntity paymentDetailsEntity);

    PaymentDetailsEntity update(UpdatePaymentDetailsRequest updatePaymentDetailsRequest);

    PaymentDetailsEntity update(PaymentDetailsEntity paymentDetailsEntity);

    void delete(PaymentDetailsEntity paymentDetailsEntity);

    PaymentDetailsEntity getById(int id);

    List<PaymentDetailsEntity> getAll();

    List<PaymentDetailsEntity> getAllFiltered(Double minAmount, Double maxAmount);
}
