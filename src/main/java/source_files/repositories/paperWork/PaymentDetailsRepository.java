package source_files.repositories.paperWork;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentDetailsEntity;

import java.util.List;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetailsEntity, Integer> {
    @Transactional
    @Query("SELECT pd FROM PaymentDetailsEntity pd " +
            "WHERE (:minAmount IS NULL AND :maxAmount IS NULL) OR " +
            "(:minAmount IS NULL AND pd.amount <= :maxAmount) OR " +
            "(:maxAmount IS NULL AND pd.amount >= :minAmount) OR " +
            "(pd.amount >= :minAmount AND pd.amount <= :maxAmount) ")
    List<PaymentDetailsEntity> findAllFiltered(
            @Param("minAmount") Double minAmount,
            @Param("maxAmount") Double maxAmount);

}
