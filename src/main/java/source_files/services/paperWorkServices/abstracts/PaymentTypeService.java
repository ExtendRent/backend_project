package source_files.services.paperWorkServices.abstracts;

import source_files.data.DTO.paperWorkDTOs.PaymentTypeDTO;
import source_files.data.requests.paperworkRequests.paymentRequests.CreatePaymentTypeRequest;
import source_files.data.requests.paperworkRequests.paymentRequests.UpdatePaymentTypeRequest;

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
