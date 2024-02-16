package source_files.services.entityServices.paperWorkEntityManagers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.enums.defaultDataEnums.DefaultPaymentType;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentTypeEntity;
import source_files.dataAccess.paperWorkRepositories.PaymentTypeEntityRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.PaymentTypeEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.PAYMENT_TYPE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PaymentypeEntityManager implements PaymentTypeEntityService {

    private final PaymentTypeEntityRepository repository;

    @Override
    public PaymentTypeEntity create(PaymentTypeEntity paymentTypeEntity) {
        return repository.save(paymentTypeEntity);
    }

    @Override
    public PaymentTypeEntity update(PaymentTypeEntity paymentTypeEntity) {
        return create(paymentTypeEntity);
    }

    @Override
    public void delete(PaymentTypeEntity paymentTypeEntity) {
        repository.delete(paymentTypeEntity);
    }

    @Override
    public PaymentTypeEntity getById(int id) {
        return repository.findById(id).orElseThrow(() ->
                new DataNotFoundException(PAYMENT_TYPE_NOT_FOUND, "Payment type bulunamadı."));
    }

    @Override
    public PaymentTypeEntity getByPaymentType(DefaultPaymentType defaultPaymentType) {
        return repository.findByPaymentType(defaultPaymentType);
    }

    @Override
    public List<PaymentTypeEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<PaymentTypeEntity> getAllByDeletedState(boolean isDeleted) {
        return repository.findAllByIsDeleted(isDeleted);
    }

    @Override
    public List<PaymentTypeEntity> getAllByActiveState(boolean isActive) {
        return repository.findAllByIsActive(isActive);
    }

}
