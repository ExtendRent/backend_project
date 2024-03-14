package source_files.repositories.paperWork;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.enums.defaultDataEnums.Status.DefaultRentalStatus;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalStatusEntity;

import java.util.Optional;

public interface RentalStatusRepository extends JpaRepository<RentalStatusEntity, Integer> {

    Optional<RentalStatusEntity> findByStatus(DefaultRentalStatus rentalStatus);
}
