package src.services.paperwork.payment_type;

import src.controllers.paperwork.requests.payment.CreatePaymentTypeRequest;
import src.controllers.paperwork.requests.payment.UpdatePaymentTypeRequest;
import src.controllers.paperwork.responses.PaymentTypeResponse;

import java.util.List;

public interface PaymentTypeService {
    void create(CreatePaymentTypeRequest createPaymentTypeRequest);

    PaymentTypeResponse update(UpdatePaymentTypeRequest updatePaymentTypeRequest);

    PaymentTypeResponse getById(int id);

    void delete(int id, boolean isHardDelete);

    void softDelete(int id);

    List<PaymentTypeResponse> getAll();

    List<PaymentTypeResponse> getAllByDeletedState(boolean isDeleted);

    List<PaymentTypeResponse> getAllByActiveState(boolean isActive);
}
