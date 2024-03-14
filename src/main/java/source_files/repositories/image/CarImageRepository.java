package source_files.repositories.image;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.imageEntities.CarImageEntity;

import java.util.Optional;

public interface CarImageRepository extends JpaRepository<CarImageEntity, Integer> {

    Optional<CarImageEntity> findByName(String name);

}
