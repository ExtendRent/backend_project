package src.repositories.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import src.data.models.image_entities.BrandImageEntity;

import java.util.Optional;

public interface BrandImageRepository extends JpaRepository<BrandImageEntity, Integer> {
    @Transactional
    Optional<BrandImageEntity> findByName(String name);
}
