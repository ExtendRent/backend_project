package source_files.dataAccess.imageRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.imageEntities.UserImageEntity;

import java.util.Optional;

public interface UserImageRepository extends JpaRepository<UserImageEntity, Integer> {

    Optional<UserImageEntity> findByName(String name);

}
