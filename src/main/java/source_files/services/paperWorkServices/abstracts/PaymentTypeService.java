package source_files.services.paperWorkServices.abstracts;

import org.apache.coyote.BadRequestException;
import source_files.data.DTO.paperWorkDTOs.PaymentTypeDTO;
import source_files.data.requests.paperworkRequests.paymentRequests.AddPaymentTypeRequest;
import source_files.data.requests.paperworkRequests.paymentRequests.UpdatePaymentTypeRequest;

import java.util.List;

public interface PaymentTypeService {
    PaymentTypeDTO add(AddPaymentTypeRequest addPaymentTypeRequest) throws BadRequestException;

    PaymentTypeDTO update(UpdatePaymentTypeRequest updatePaymentTypeRequest);

    PaymentTypeDTO getById(int id);

    void delete(int id);

    void softDelete(int id);

    List<PaymentTypeDTO> getAll();

    List<PaymentTypeDTO> getAllByDeletedState(boolean isDeleted);
}
