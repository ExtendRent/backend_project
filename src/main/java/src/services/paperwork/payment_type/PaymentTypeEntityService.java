package src.services.paperwork.payment_type;

import src.controllers.paperwork.requests.payment.CreatePaymentTypeRequest;
import src.controllers.paperwork.requests.payment.UpdatePaymentTypeRequest;
import src.data.enums.default_data_enums.DefaultPaymentType;
import src.data.models.paperwork_entities.paymentEntities.PaymentTypeEntity;

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
