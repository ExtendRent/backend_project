package src.repositories.paperwork;

import org.springframework.data.jpa.repository.JpaRepository;
import src.data.enums.default_data_enums.status.DefaultRentalStatus;
import src.data.models.paperwork_entities.rentalEntities.RentalStatusEntity;

import java.util.Optional;

public interface RentalStatusRepository extends JpaRepository<RentalStatusEntity, Integer> {

    Optional<RentalStatusEntity> findByStatus(DefaultRentalStatus rentalStatus);
}
