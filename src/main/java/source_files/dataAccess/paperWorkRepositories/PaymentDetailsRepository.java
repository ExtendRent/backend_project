package source_files.dataAccess.paperWorkRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.paperWorkEntities.paymentEntities.DiscountCodeEntity;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;

import java.util.List;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetailsEntity, Integer> {


    List<PaymentDetailsEntity> findAllByIsDeletedFalse();
    List<PaymentDetailsEntity> findAllByIsDeletedTrue();
}
