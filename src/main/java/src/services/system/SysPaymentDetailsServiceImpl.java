package src.services.system;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.controllers.paperwork.requests.payment.UpdatePaymentDetailsRequest;
import src.core.exception.DataNotFoundException;
import src.data.models.paperwork_entities.paymentEntities.PaymentDetailsEntity;
import src.repositories.paperwork.PaymentDetailsRepository;

import java.util.List;

import static src.core.exception.exception_types.NotFoundExceptionType.PAYMENT_DETAILS_DATA_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class SysPaymentDetailsServiceImpl implements SysPaymentDetailsService {
    private final PaymentDetailsRepository repository;

    @Override
    public PaymentDetailsEntity create(PaymentDetailsEntity paymentDetailsEntity) {
        return repository.save(paymentDetailsEntity);
    }

    @Override
    public PaymentDetailsEntity update(UpdatePaymentDetailsRequest updatePaymentDetailsRequest) {
        PaymentDetailsEntity paymentDetailsEntity = PaymentDetailsEntity.paymentDetailsBuilder()
                .id(updatePaymentDetailsRequest.getId())
                .amount(updatePaymentDetailsRequest.getAmount())
                .build();
        return repository.save(paymentDetailsEntity);
    }

    @Override
    public PaymentDetailsEntity update(PaymentDetailsEntity paymentDetailsEntity) {
        return repository.save(paymentDetailsEntity);
    }

    @Override
    public void delete(PaymentDetailsEntity paymentDetailsEntity) {
        repository.delete(paymentDetailsEntity);
    }

    @Override
    public PaymentDetailsEntity getById(int id) {
        return repository.findById(id).orElseThrow(
                () -> new DataNotFoundException(PAYMENT_DETAILS_DATA_NOT_FOUND));
    }

    @Override
    public List<PaymentDetailsEntity> getAll() {
        return repository.findAll();
    }

    @Override
    public List<PaymentDetailsEntity> getAllFiltered(Double minAmount, Double maxAmount) {
        return repository.findAllFiltered(
                minAmount, maxAmount);
    }

}
