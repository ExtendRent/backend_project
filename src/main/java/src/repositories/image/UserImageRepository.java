package src.repositories.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import src.data.models.image_entities.UserImageEntity;

import java.util.Optional;

public interface UserImageRepository extends JpaRepository<UserImageEntity, Integer> {

    @Transactional
    Optional<UserImageEntity> findByName(String name);

    boolean existsByName(String name);

    @Query("SELECT id FROM UserImageEntity WHERE name = :name")
    int findIdByName(String name);
}
