package src.repository.image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BrandImageRepository extends JpaRepository<BrandImageEntity, Integer> {

    Optional<BrandImageEntity> findByName(String name);
}
