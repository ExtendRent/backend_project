package source_files.services.systemServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.requests.paperworkRequests.paymentRequests.UpdatePaymentDetailsRequest;
import source_files.dataAccess.paperWorkRepositories.PaymentDetailsRepository;
import source_files.exception.DataNotFoundException;

import java.util.List;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.PAYMENT_DETAILS_DATA_NOT_FOUND;

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
