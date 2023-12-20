package source_files.dataAccess.paperWorkRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.paperWorkEntities.paymentEntities.DiscountCodeEntity;
import source_files.data.models.userEntities.AdminEntity;

import java.util.List;
import java.util.Optional;

public interface DiscountCodeRepository extends JpaRepository<DiscountCodeEntity, Integer> {

    boolean existsByDiscountCode(String code);

    boolean existsById(int id);


    Optional<DiscountCodeEntity> findByDiscountCode(String code);

    List<DiscountCodeEntity> findAllByIsDeletedFalse();
    List<DiscountCodeEntity> findAllByIsDeletedTrue();
}