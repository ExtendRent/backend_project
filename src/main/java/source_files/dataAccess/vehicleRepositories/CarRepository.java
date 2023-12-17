package source_files.dataAccess.vehicleRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.vehicleEntities.CarEntity;

public interface CarRepository extends JpaRepository<CarEntity, Integer> {
    boolean existsByLicensePlate(String plate);
}