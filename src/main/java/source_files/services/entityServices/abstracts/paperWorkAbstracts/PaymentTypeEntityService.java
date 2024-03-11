package source_files.services.entityServices.abstracts.paperWorkAbstracts;

import source_files.data.enums.defaultDataEnums.DefaultPaymentType;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentTypeEntity;
import source_files.data.requests.paperworkRequests.paymentRequests.CreatePaymentTypeRequest;
import source_files.data.requests.paperworkRequests.paymentRequests.UpdatePaymentTypeRequest;

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
