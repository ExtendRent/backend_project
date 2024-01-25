package source_files.dataAccess.vehicleRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import source_files.data.models.vehicleEntities.CarEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends JpaRepository<CarEntity, Integer> {


    @Query("SELECT c FROM CarEntity c WHERE " +
            "(:startDate IS NULL OR c.availabilityDate <= :startDate) AND " +
            "(:endDate IS NULL OR c.availabilityDate < :endDate) AND " +
            "(:startPrice IS NULL OR :startPrice <= c.rentalPrice) AND " +
            "(:endPrice IS NULL OR :endPrice >= c.rentalPrice) AND " +
            "(:isDeleted IS NULL OR :isDeleted = c.isDeleted) AND " +
            "(:isAvailable IS NULL OR :isAvailable = c.isAvailable) AND " +
            "(:colorId IS NULL OR :colorId = c.colorEntity.id) AND " +
            "(:colorId IS NULL OR :seat = c.seat) AND " +
            "(:colorId IS NULL OR :luggage = c.luggage) AND " +
            "(:modelId IS NULL OR :modelId = c.carModelEntity.id) AND " +
            "(:startYear IS NULL OR :startYear <= c.year) AND " +
            "(:endYear IS NULL OR :endYear >= c.year) AND " +
            "(:brandId IS NULL OR :brandId = c.carModelEntity.brandEntity.id) AND " +
            "(:fuelTypeId IS NULL OR :fuelTypeId = c.fuelTypeEntity.id) AND " +
            "(:shiftTypeId IS NULL OR :shiftTypeId = c.shiftTypeEntity.id)")
    List<CarEntity> findAllFiltered(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate,

            @Param("startPrice") Integer startPrice,
            @Param("endPrice") Integer endPrice,

            @Param("isDeleted") Boolean isDeleted,
            @Param("isAvailable") Boolean isAvailable,

            @Param("colorId") Integer colorId,
            @Param("seat") Integer seat,
            @Param("luggage") Integer luggage,
            @Param("modelId") Integer modelId,
            @Param("startYear") Integer startYear,
            @Param("endYear") Integer endYear,
            @Param("brandId") Integer brandId,
            @Param("fuelTypeId") Integer fuelTypeId,
            @Param("shiftTypeId") Integer shiftTypeId);

    List<CarEntity> findAllByCarModelEntity_BrandEntity_Id(int brandEntityId);

    boolean existsByLicensePlate(String plate);

    boolean existsByLicensePlateAndIdNot(String plate, int id);

    List<CarEntity> findAllByIsDeleted(boolean isDeleted);

    List<CarEntity> findAllByIsAvailable(boolean isAvailable);

    List<CarEntity> findAllByColorEntity_Id(int id);

    List<CarEntity> findAllByRentalPriceBetween(double startPrice, double endPrice);

    List<CarEntity> findAllByAvailabilityDateBetween(LocalDate startDate, LocalDate endDate);

    List<CarEntity> findAllByCarModelEntity_Id(int id);

    List<CarEntity> findAllByYearBetween(int year1, int year2);
}