package source_files.dataAccess.vehicleRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.vehicleEntities.CarEntity;

import java.util.List;

public interface CarRepository extends JpaRepository<CarEntity, Integer> {
    boolean existsByLicensePlate(String plate);

    List<CarEntity> findAllByIsDeletedFalse();

    List<CarEntity> findAllByIsDeletedTrue();

    List<CarEntity> findAllByIsAvailableTrue();

    List<CarEntity> findAllByIsAvailableFalse();

    List<CarEntity> findAllByColorEntity_Id(int id);

    List<CarEntity> findAllByCarModelEntity_Id(int id);

    List<CarEntity> findAllByYearBetween(int year1, int year2);
}