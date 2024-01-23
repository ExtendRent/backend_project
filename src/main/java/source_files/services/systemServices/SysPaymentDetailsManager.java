package source_files.services.systemServices;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.data.requests.paperworkRequests.paymentRequests.UpdatePaymentDetailsRequest;
import source_files.dataAccess.paperWorkRepositories.PaymentDetailsRepository;
import source_files.exception.DataNotFoundException;

import java.util.List;

import static source_files.data.types.itemTypes.ItemType.PAYMENT_DETAILS;
import static source_files.exception.exceptionTypes.NotFoundExceptionType.PAYMENT_DETAILS_DATA_NOT_FOUND;

@Service
@AllArgsConstructor
public class SysPaymentDetailsManager implements SysPaymentDetailsService {
    private final PaymentDetailsRepository paymentDetailsRepository;

    private final ModelMapperService modelMapperService;

    @Override
    public PaymentDetailsEntity create(PaymentDetailsEntity paymentDetailsEntity) {
        paymentDetailsEntity.setItemType(PAYMENT_DETAILS);
        return this.paymentDetailsRepository.save(paymentDetailsEntity);
    }

    @Override
    public PaymentDetailsEntity update(UpdatePaymentDetailsRequest updatePaymentDetailsRequest) {
        return this.paymentDetailsRepository
                .save(this.modelMapperService.forRequest()
                        .map(updatePaymentDetailsRequest, PaymentDetailsEntity.class));
    }

    @Override
    public void delete(PaymentDetailsEntity paymentDetailsEntity) {
        this.paymentDetailsRepository.delete(paymentDetailsEntity);
    }

    @Override
    public PaymentDetailsEntity getById(int id) {
        return this.paymentDetailsRepository.findById(id).orElseThrow(
                () -> new DataNotFoundException(PAYMENT_DETAILS_DATA_NOT_FOUND, "ödeme detayı bulunamadı"));
    }

    @Override
    public List<PaymentDetailsEntity> getAll() {
        return this.paymentDetailsRepository.findAll();
    }
}
