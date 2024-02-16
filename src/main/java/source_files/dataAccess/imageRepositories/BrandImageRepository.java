package source_files.dataAccess.imageRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.imageEntities.BrandImageEntity;

import java.util.Optional;

public interface BrandImageRepository extends JpaRepository<BrandImageEntity, Integer> {
    Optional<BrandImageEntity> findByName(String name);
}
