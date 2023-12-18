package source_files.dataAccess.paperWorkRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;


public interface RentalRepository extends JpaRepository<RentalEntity, Integer> {

}
