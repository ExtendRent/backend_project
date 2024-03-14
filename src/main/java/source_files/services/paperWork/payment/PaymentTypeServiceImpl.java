package source_files.services.paperWork.payment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.controllers.paperWork.dtos.PaymentTypeDTO;
import source_files.controllers.paperWork.requests.paymentRequests.CreatePaymentTypeRequest;
import source_files.controllers.paperWork.requests.paymentRequests.UpdatePaymentTypeRequest;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentTypeEntity;
import source_files.services.BusinessRules.paperWork.PaymentTypeRules;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.PaymentTypeEntityService;
import source_files.services.paperWork.abstracts.PaymentTypeService;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentTypeServiceImpl implements PaymentTypeService {
    private final PaymentTypeEntityService entityService;
    private final PaymentTypeRules rules;

    @Override
    public void create(CreatePaymentTypeRequest createPaymentTypeRequest) {
        createPaymentTypeRequest = rules.fix(createPaymentTypeRequest);
        rules.check(createPaymentTypeRequest);
        entityService.create(createPaymentTypeRequest);
    }

    @Override
    public PaymentTypeDTO update(UpdatePaymentTypeRequest updatePaymentTypeRequest) {
        updatePaymentTypeRequest = rules.fix(updatePaymentTypeRequest);
        rules.check(updatePaymentTypeRequest);
        return entityService.update(updatePaymentTypeRequest).toModel();
    }

    @Override
    public PaymentTypeDTO getById(int id) {
        return entityService.getById(id).toModel();
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
        entityService.update(paymentTypeEntity);
    }

    @Override
    public List<PaymentTypeDTO> getAll() {
        return maptoDTOList(entityService.getAll());
    }

    @Override
    public List<PaymentTypeDTO> getAllByDeletedState(boolean isDeleted) {
        return maptoDTOList(entityService.getAllByDeletedState(isDeleted));
    }

    @Override
    public List<PaymentTypeDTO> getAllByActiveState(boolean isActive) {
        return maptoDTOList(entityService.getAllByActiveState(isActive));
    }

    private List<PaymentTypeDTO> maptoDTOList(List<PaymentTypeEntity> payments) {
        rules.checkDataList(payments);
        return payments.stream().map(PaymentTypeEntity::toModel).toList();
    }


}
