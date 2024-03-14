package source_files.services.system;

import source_files.controllers.paperWork.requests.paymentRequests.UpdatePaymentDetailsRequest;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;

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
