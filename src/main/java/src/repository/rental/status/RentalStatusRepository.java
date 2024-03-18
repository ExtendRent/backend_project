package src.repository.rental.status;

import org.springframework.data.jpa.repository.JpaRepository;
import src.service.rental.status.model.DefaultRentalStatus;

import java.util.Optional;

public interface RentalStatusRepository extends JpaRepository<RentalStatusEntity, Integer> {

    Optional<RentalStatusEntity> findByStatus(DefaultRentalStatus rentalStatus);
}
