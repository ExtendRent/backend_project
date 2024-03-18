package src.repository.payment.type;

import org.springframework.data.jpa.repository.JpaRepository;
import src.service.payment.type.model.DefaultPaymentType;

import java.util.List;

public interface PaymentTypeRepository extends JpaRepository<PaymentTypeEntity, Integer> {

    List<PaymentTypeEntity> findAllByIsDeleted(boolean isDeleted);

    PaymentTypeEntity findByPaymentType(DefaultPaymentType defaultPaymentType);

    List<PaymentTypeEntity> findAllByIsActive(boolean isActive);

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);

}