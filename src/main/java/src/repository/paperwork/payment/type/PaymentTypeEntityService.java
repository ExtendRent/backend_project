package src.repository.paperwork.payment.type;

import src.controller.paperwork.payment.requests.CreatePaymentTypeRequest;
import src.controller.paperwork.payment.requests.UpdatePaymentTypeRequest;
import src.service.paperwork.payment.type.model.DefaultPaymentType;

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
