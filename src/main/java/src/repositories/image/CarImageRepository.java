package src.repositories.image;

import org.springframework.data.jpa.repository.JpaRepository;
import src.data.models.image_entities.CarImageEntity;

import java.util.Optional;

public interface CarImageRepository extends JpaRepository<CarImageEntity, Integer> {

    Optional<CarImageEntity> findByName(String name);

}
