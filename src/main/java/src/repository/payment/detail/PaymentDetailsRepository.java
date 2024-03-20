package src.repository.payment.detail;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentDetailsRepository extends JpaRepository<PaymentDetailsEntity, Integer> {

    @Query("SELECT pd FROM PaymentDetailsEntity pd " +
            "WHERE (:minAmount IS NULL AND :maxAmount IS NULL) OR " +
            "(:minAmount IS NULL AND pd.amount <= :maxAmount) OR " +
            "(:maxAmount IS NULL AND pd.amount >= :minAmount) OR " +
            "(pd.amount >= :minAmount AND pd.amount <= :maxAmount) ")
    List<PaymentDetailsEntity> findAllFiltered(
            @Param("minAmount") Double minAmount,
            @Param("maxAmount") Double maxAmount);

}
