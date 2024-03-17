package src.repository.image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserImageRepository extends JpaRepository<UserImageEntity, Integer> {


    Optional<UserImageEntity> findByName(String name);

    boolean existsByName(String name);

    @Query("SELECT id FROM UserImageEntity WHERE name = :name")
    int findIdByName(String name);
}
