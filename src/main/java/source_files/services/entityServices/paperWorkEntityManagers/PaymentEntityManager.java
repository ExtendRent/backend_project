package source_files.services.entityServices.paperWorkEntityManagers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;
import source_files.services.entityServices.abstracts.paperWorkAbstracts.PaymentEntityService;

import java.util.List;

@Service
@AllArgsConstructor
public class PaymentEntityManager implements PaymentEntityService {
    @Override
    public PaymentDetailsEntity add(PaymentDetailsEntity paymentDetailsEntity) {
        return null;
    }

    @Override
    public PaymentDetailsEntity update(PaymentDetailsEntity paymentDetailsEntity) {
        return null;
    }

    @Override
    public void delete(PaymentDetailsEntity paymentDetailsEntity) {

    }

    @Override
    public PaymentDetailsEntity getById(int id) {
        return null;
    }

    @Override
    public List<PaymentDetailsEntity> getAll() {
        return null;
    }
}
