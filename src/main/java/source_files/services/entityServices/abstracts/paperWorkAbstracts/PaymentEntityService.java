package source_files.services.entityServices.abstracts.paperWorkAbstracts;

import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;

import java.util.List;

public interface PaymentEntityService {

    PaymentDetailsEntity add(PaymentDetailsEntity paymentDetailsEntity);

    PaymentDetailsEntity update(PaymentDetailsEntity paymentDetailsEntity);

    void delete(PaymentDetailsEntity paymentDetailsEntity);

    PaymentDetailsEntity getById(int id);

    List<PaymentDetailsEntity> getAll();

}
