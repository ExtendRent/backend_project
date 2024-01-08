package source_files.services.paperWorkServices;

import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.paperWorkDTOs.PaymentTypeDTO;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentTypeEntity;
import source_files.data.requests.itemRequests.paymentRequests.AddPaymentTypeRequest;
import source_files.data.requests.itemRequests.paymentRequests.UpdatePaymentTypeRequest;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.PaymentTypeEntityService;
import source_files.services.paperWorkServices.abstracts.PaymentTypeService;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentTypeManager implements PaymentTypeService {
    private final PaymentTypeEntityService paymentTypeEntityService;
    private final ModelMapperService modelMapperService;

    @Override
    public PaymentTypeDTO add(AddPaymentTypeRequest addPaymentTypeRequest) throws BadRequestException {
        return this.modelMapperService.forResponse().map(this.paymentTypeEntityService.add(
                this.modelMapperService.forRequest()
                        .map(addPaymentTypeRequest, PaymentTypeEntity.class)), PaymentTypeDTO.class);
    }

    @Override
    public PaymentTypeDTO update(UpdatePaymentTypeRequest updatePaymentTypeRequest) {
        return this.modelMapperService.forResponse().map(this.paymentTypeEntityService.update(
                this.modelMapperService.forRequest()
                        .map(updatePaymentTypeRequest, PaymentTypeEntity.class)), PaymentTypeDTO.class);
    }

    @Override
    public PaymentTypeDTO getById(int id) {
        return this.modelMapperService.forResponse().map(this.paymentTypeEntityService.getById(id), PaymentTypeDTO.class);
    }

    @Override
    public void delete(int id) {
        this.paymentTypeEntityService.delete(this.paymentTypeEntityService.getById(id));
    }

    @Override
    public void softDelete(int id) {
        PaymentTypeEntity paymentTypeEntity = this.paymentTypeEntityService.getById(id);
        paymentTypeEntity.setIsDeleted(true);
        this.paymentTypeEntityService.add(paymentTypeEntity);
    }

    @Override
    public List<PaymentTypeDTO> getAll() {
        return this.paymentTypeEntityService.getAll().stream()
                .map(paymentTypeEntity -> this.modelMapperService.forResponse()
                        .map(paymentTypeEntity, PaymentTypeDTO.class)).toList();
    }

    @Override
    public List<PaymentTypeDTO> getAllByIsDeletedFalse() {
        return null;
    }

    @Override
    public List<PaymentTypeDTO> getAllByIsDeletedTrue() {
        return null;
    }
}
