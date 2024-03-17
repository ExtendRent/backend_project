package src.repository.image;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarImageRepository extends JpaRepository<CarImageEntity, Integer> {

    Optional<CarImageEntity> findByName(String name);

}
