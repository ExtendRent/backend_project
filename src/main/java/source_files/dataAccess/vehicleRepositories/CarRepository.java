package source_files.dataAccess.vehicleRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.vehicleEntities.CarEntity;

import java.util.List;

public interface CarRepository extends JpaRepository<CarEntity, Integer> {

    List<CarEntity> findAllByCarModelEntity_BrandEntity_Id(int brandEntityId);

    boolean existsByLicensePlate(String plate);

    boolean existsByLicensePlateAndIdNot(String plate, int id);

    List<CarEntity> findAllByIsDeleted(boolean isDeleted);

    List<CarEntity> findAllByIsAvailable(boolean isAvailable);

    List<CarEntity> findAllByColorEntity_Id(int id);

    List<CarEntity> findAllByCarModelEntity_Id(int id);

    List<CarEntity> findAllByYearBetween(int year1, int year2);
}