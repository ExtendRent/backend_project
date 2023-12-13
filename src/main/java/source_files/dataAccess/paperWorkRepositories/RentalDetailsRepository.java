package source_files.dataAccess.paperWorkRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalDetailsEntity;

public interface RentalDetailsRepository extends JpaRepository<RentalDetailsEntity, Integer> {
}
