package source_files.dataAccess.paperWorkRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetailsEntity, Integer> {
}
