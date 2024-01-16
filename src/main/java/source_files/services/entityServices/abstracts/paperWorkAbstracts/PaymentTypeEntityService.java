package source_files.services.entityServices.abstracts.paperWorkAbstracts;

import source_files.data.models.paperWorkEntities.paymentEntities.PaymentTypeEntity;
import source_files.data.types.PaymentType;

import java.util.List;

public interface PaymentTypeEntityService {
    PaymentTypeEntity add(PaymentTypeEntity paymentTypeEntity);

    PaymentTypeEntity update(PaymentTypeEntity paymentTypeEntity);

    void delete(PaymentTypeEntity paymentTypeEntity);

    PaymentTypeEntity getById(int id);

    PaymentTypeEntity getByPaymentType(PaymentType paymentType);

    List<PaymentTypeEntity> getAll();

    List<PaymentTypeEntity> getAllByDeletedState(boolean isDeleted);

}
