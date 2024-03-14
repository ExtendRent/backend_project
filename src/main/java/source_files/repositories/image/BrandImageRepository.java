package source_files.repositories.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import source_files.data.models.imageEntities.BrandImageEntity;

import java.util.Optional;

public interface BrandImageRepository extends JpaRepository<BrandImageEntity, Integer> {
    @Transactional
    Optional<BrandImageEntity> findByName(String name);
}
