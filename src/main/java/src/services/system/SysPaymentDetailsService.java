package src.services.system;

import src.controllers.paperwork.requests.payment.UpdatePaymentDetailsRequest;
import src.data.models.paperwork_entities.paymentEntities.PaymentDetailsEntity;

import java.util.List;

public interface SysPaymentDetailsService {

    PaymentDetailsEntity create(PaymentDetailsEntity paymentDetailsEntity);

    PaymentDetailsEntity update(UpdatePaymentDetailsRequest updatePaymentDetailsRequest);

    PaymentDetailsEntity update(PaymentDetailsEntity paymentDetailsEntity);

    void delete(PaymentDetailsEntity paymentDetailsEntity);

    PaymentDetailsEntity getById(int id);

    List<PaymentDetailsEntity> getAll();

    List<PaymentDetailsEntity> getAllFiltered(Double minAmount, Double maxAmount);
}
