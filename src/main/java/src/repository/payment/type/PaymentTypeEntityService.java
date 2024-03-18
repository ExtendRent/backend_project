package src.repository.payment.type;

import src.controller.payment.requests.CreatePaymentTypeRequest;
import src.controller.payment.requests.UpdatePaymentTypeRequest;
import src.service.payment.type.model.DefaultPaymentType;

import java.util.List;

public interface PaymentTypeEntityService {
    PaymentTypeEntity create(CreatePaymentTypeRequest createPaymentTypeRequest);

    PaymentTypeEntity update(UpdatePaymentTypeRequest updatePaymentTypeRequest);

    PaymentTypeEntity update(PaymentTypeEntity paymentTypeEntity);

    void delete(PaymentTypeEntity paymentTypeEntity);

    PaymentTypeEntity getById(int id);

    PaymentTypeEntity getByPaymentType(DefaultPaymentType defaultPaymentType);

    List<PaymentTypeEntity> getAll();

    List<PaymentTypeEntity> getAllByDeletedState(boolean isDeleted);

    List<PaymentTypeEntity> getAllByActiveState(boolean isActive);

}
