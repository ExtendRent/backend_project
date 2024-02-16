package source_files.dataAccess.imageRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.imageEntities.CarImageEntity;

import java.util.Optional;

public interface CarImageRepository extends JpaRepository<CarImageEntity, Integer> {

    Optional<CarImageEntity> findByName(String name);

}
