package source_files.dataAccess.paperWorkRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.enums.defaultDataEnums.DefaultPaymentType;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentTypeEntity;

import java.util.List;

public interface PaymentTypeEntityRepository extends JpaRepository<PaymentTypeEntity, Integer> {

    List<PaymentTypeEntity> findAllByIsDeleted(boolean isDeleted);

    PaymentTypeEntity findByPaymentType(DefaultPaymentType defaultPaymentType);

    List<PaymentTypeEntity> findAllByIsActive(boolean isActive);

    boolean existsByNameIgnoreCase(String name);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);

}