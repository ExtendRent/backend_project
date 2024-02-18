package source_files.services.paperWorkServices.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.DTO.paperWorkDTOs.PaymentTypeDTO;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentTypeEntity;
import source_files.data.requests.paperworkRequests.paymentRequests.CreatePaymentTypeRequest;
import source_files.data.requests.paperworkRequests.paymentRequests.UpdatePaymentTypeRequest;
import source_files.services.BusinessRules.paperWork.PaymentTypeBusinessRules;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.PaymentTypeEntityService;
import source_files.services.paperWorkServices.abstracts.PaymentTypeService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentTypeManager implements PaymentTypeService {
    private final PaymentTypeEntityService entityService;
    private final ModelMapperService mapper;

    private final PaymentTypeBusinessRules rules;

    @Override
    public void create(CreatePaymentTypeRequest createPaymentTypeRequest) {
        PaymentTypeEntity paymentTypeEntity = mapper.forRequest()
                .map(createPaymentTypeRequest, PaymentTypeEntity.class);
        entityService.create(paymentTypeEntity);
    }


    @Override
    public PaymentTypeDTO update(UpdatePaymentTypeRequest updatePaymentTypeRequest) {
        PaymentTypeEntity paymentType = mapper.forRequest().map(updatePaymentTypeRequest, PaymentTypeEntity.class);
        return mapper.forResponse().map(entityService.update(paymentType), PaymentTypeDTO.class);
    }

    @Override
    public PaymentTypeDTO getById(int id) {
        return mapper.forResponse().map(entityService.getById(id), PaymentTypeDTO.class);
    }

    @Override
    public void delete(int id, boolean hardDelete) {
        if (hardDelete) {
            entityService.delete(entityService.getById(id));
        } else {
            softDelete(id);
        }

    }

    @Override
    public void softDelete(int id) {
        PaymentTypeEntity paymentTypeEntity = entityService.getById(id);
        paymentTypeEntity.setIsDeleted(true);
        paymentTypeEntity.setActive(false);
        paymentTypeEntity.setDeletedAt(LocalDateTime.now());
        entityService.create(paymentTypeEntity);
    }

    @Override
    public List<PaymentTypeDTO> getAll() {
        return rules.checkDataList(entityService.getAll()).stream()
                .map(paymentTypeEntity -> mapper.forResponse()
                        .map(paymentTypeEntity, PaymentTypeDTO.class)
                ).toList();

    }

    @Override
    public List<PaymentTypeDTO> getAllByDeletedState(boolean isDeleted) {
        return rules.checkDataList(entityService.getAllByDeletedState(isDeleted)).stream()
                .map(discountCode -> mapper.forResponse()
                        .map(discountCode, PaymentTypeDTO.class)).toList();
    }

    @Override
    public List<PaymentTypeDTO> getAllByActiveState(boolean isActive) {
        return rules.checkDataList(entityService.getAllByActiveState(isActive)).stream()
                .map(discountCode -> mapper.forResponse()
                        .map(discountCode, PaymentTypeDTO.class)).toList();
    }


}
