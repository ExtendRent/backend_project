package source_files.services.paperWork.abstracts;

import source_files.controllers.paperWork.dtos.PaymentTypeDTO;
import source_files.controllers.paperWork.requests.payment.CreatePaymentTypeRequest;
import source_files.controllers.paperWork.requests.payment.UpdatePaymentTypeRequest;

import java.util.List;

public interface PaymentTypeService {
    void create(CreatePaymentTypeRequest createPaymentTypeRequest);

    PaymentTypeDTO update(UpdatePaymentTypeRequest updatePaymentTypeRequest);

    PaymentTypeDTO getById(int id);

    void delete(int id, boolean isHardDelete);

    void softDelete(int id);

    List<PaymentTypeDTO> getAll();

    List<PaymentTypeDTO> getAllByDeletedState(boolean isDeleted);

    List<PaymentTypeDTO> getAllByActiveState(boolean isActive);
}
