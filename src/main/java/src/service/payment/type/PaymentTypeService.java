package src.service.payment.type;

import src.controller.payment.type.request.CreatePaymentTypeRequest;
import src.controller.payment.type.request.UpdatePaymentTypeRequest;
import src.controller.payment.type.response.PaymentTypeResponse;

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
