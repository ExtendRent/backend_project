package source_files.services.paperWorkServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.paperWorkDTOs.PaymentTypeDTO;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentTypeEntity;
import source_files.data.requests.paperworkRequests.paymentRequests.CreatePaymentTypeRequest;
import source_files.data.requests.paperworkRequests.paymentRequests.UpdatePaymentTypeRequest;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.PaymentTypeEntityService;
import source_files.services.paperWorkServices.abstracts.PaymentTypeService;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentTypeManager implements PaymentTypeService {
    private final PaymentTypeEntityService paymentTypeEntityService;
    private final ModelMapperService modelMapperService;

    @Override
    public void create(CreatePaymentTypeRequest createPaymentTypeRequest) {
        this.paymentTypeEntityService.create(
                this.modelMapperService.forRequest()
                        .map(createPaymentTypeRequest, PaymentTypeEntity.class));
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
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            this.paymentTypeEntityService.delete(this.paymentTypeEntityService.getById(id));
        } else {
            this.softDelete(id);
        }

    }

    @Override
    public void softDelete(int id) {
        PaymentTypeEntity paymentTypeEntity = this.paymentTypeEntityService.getById(id);
        paymentTypeEntity.setIsDeleted(true);
        this.paymentTypeEntityService.create(paymentTypeEntity);
    }

    @Override
    public List<PaymentTypeDTO> getAll() {
        return this.paymentTypeEntityService.getAll().stream()
                .map(paymentTypeEntity -> this.modelMapperService.forResponse()
                        .map(paymentTypeEntity, PaymentTypeDTO.class)
                ).toList();
    }

    @Override
    public List<PaymentTypeDTO> getAllByDeletedState(boolean isDeleted) {
        return this.paymentTypeEntityService.getAllByDeletedState(isDeleted).stream()
                .map(paymentTypeEntity -> this.modelMapperService.forResponse()
                        .map(paymentTypeEntity, PaymentTypeDTO.class)
                ).toList();
    }

}
