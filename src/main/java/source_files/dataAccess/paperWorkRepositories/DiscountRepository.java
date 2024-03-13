package source_files.dataAccess.paperWorkRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.paperWorkEntities.paymentEntities.DiscountEntity;

import java.util.List;
import java.util.Optional;

public interface DiscountRepository extends JpaRepository<DiscountEntity, Integer> {

    boolean existsByDiscountCode(String code);

    boolean existsByDiscountCodeAndIdNot(String code, int id);

    Optional<DiscountEntity> findByDiscountCode(String code);

    List<DiscountEntity> findAllByIsDeleted(boolean isDeleted);

    List<DiscountEntity> findAllByIsActive(boolean isActive);

}