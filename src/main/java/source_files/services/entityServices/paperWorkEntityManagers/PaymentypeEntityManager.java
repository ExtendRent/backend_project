package source_files.services.entityServices.paperWorkEntityManagers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentTypeEntity;
import source_files.data.types.itemTypes.PaymentType;
import source_files.dataAccess.paperWorkRepositories.PaymentTypeEntityRepository;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.PaymentTypeEntityService;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.PAYMENT_TYPE_NOT_FOUND;

@Service
@AllArgsConstructor
public class PaymentypeEntityManager implements PaymentTypeEntityService {

    private final PaymentTypeEntityRepository paymentTypeEntityRepository;

    @Override
    public PaymentTypeEntity create(PaymentTypeEntity paymentTypeEntity) {
        return this.paymentTypeEntityRepository.save(paymentTypeEntity);
    }

    @Override
    public PaymentTypeEntity update(PaymentTypeEntity paymentTypeEntity) {
        return this.create(paymentTypeEntity);
    }

    @Override
    public void delete(PaymentTypeEntity paymentTypeEntity) {
        this.paymentTypeEntityRepository.delete(paymentTypeEntity);
    }

    @Override
    public PaymentTypeEntity getById(int id) {
        return this.paymentTypeEntityRepository.findById(id).orElseThrow(() ->
                new DataNotFoundException(PAYMENT_TYPE_NOT_FOUND, "Payment type bulunamadı."));
    }

    @Override
    public PaymentTypeEntity getByPaymentType(PaymentType paymentType) {
        return this.paymentTypeEntityRepository.findByPaymentType(paymentType);
    }

    @Override
    public List<PaymentTypeEntity> getAll() {
        return this.paymentTypeEntityRepository.findAll();
    }

    @Override
    public List<PaymentTypeEntity> getAllByDeletedState(boolean isDeleted) {
        return this.paymentTypeEntityRepository.findAllByIsDeleted(isDeleted);
    }


}
