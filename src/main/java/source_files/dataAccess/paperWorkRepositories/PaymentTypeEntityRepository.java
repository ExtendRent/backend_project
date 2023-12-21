package source_files.dataAccess.paperWorkRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.paperWorkEntities.paymentEntities.PaymentTypeEntity;

import java.util.List;

public interface PaymentTypeEntityRepository extends JpaRepository<PaymentTypeEntity, Integer> {

    List<PaymentTypeEntity> findAllByIsDeletedFalse();

    List<PaymentTypeEntity> findAllByIsDeletedTrue();

}