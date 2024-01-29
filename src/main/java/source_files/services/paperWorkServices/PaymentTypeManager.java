package source_files.services.paperWorkServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.paperWorkDTOs.PaymentTypeDTO;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentTypeEntity;
import source_files.data.requests.paperworkRequests.paymentRequests.CreatePaymentTypeRequest;
import source_files.data.requests.paperworkRequests.paymentRequests.UpdatePaymentTypeRequest;
import source_files.services.BusinessRules.paperWork.PaymentBusinessRules;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.PaymentTypeEntityService;
import source_files.services.paperWorkServices.abstracts.PaymentTypeService;

import java.time.LocalDateTime;
import java.util.List;

import static source_files.data.types.itemTypes.ItemType.PAYMENT_TYPE;

@Service
@RequiredArgsConstructor
public class PaymentTypeManager implements PaymentTypeService {
    private final PaymentTypeEntityService paymentTypeEntityService;
    private final ModelMapperService modelMapperService;

    private final PaymentBusinessRules paymentBusinessRules;

    @Override
    public void create(CreatePaymentTypeRequest createPaymentTypeRequest) {
        PaymentTypeEntity paymentTypeEntity = this.modelMapperService.forRequest()
                .map(createPaymentTypeRequest, PaymentTypeEntity.class);

        paymentTypeEntity.setItemType(PAYMENT_TYPE);
        this.paymentTypeEntityService.create(paymentTypeEntity);
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
        paymentTypeEntity.setDeletedAt(LocalDateTime.now());
        this.paymentTypeEntityService.create(paymentTypeEntity);
    }

    @Override
    public List<PaymentTypeDTO> getAll() {
        return this.paymentBusinessRules.checkDataList(this.paymentTypeEntityService.getAll()).stream()
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
