package src.repositories.paperwork;

import org.springframework.data.jpa.repository.JpaRepository;
import src.data.enums.default_data_enums.DefaultPaymentType;
import src.data.models.paperwork_entities.paymentEntities.PaymentTypeEntity;

import java.util.List;

public interface PaymentTypeEntityRepository extends JpaRepository<PaymentTypeEntity, Integer> {

    List<PaymentTypeEntity> findAllByIsDeleted(boolean isDeleted);

    PaymentTypeEntity findByPaymentType(DefaultPaymentType defaultPaymentType);

    List<PaymentTypeEntity> findAllByIsActive(boolean isActive);

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);

}