package src.service.payment.type;

import src.controller.payment.requests.CreatePaymentTypeRequest;
import src.controller.payment.requests.UpdatePaymentTypeRequest;
import src.controller.payment.responses.PaymentTypeResponse;

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
