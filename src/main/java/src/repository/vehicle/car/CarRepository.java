package src.repository.vehicle.car;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CarRepository extends JpaRepository<CarEntity, Integer> {


    @Query("SELECT c FROM CarEntity c WHERE " +
            "(:startPrice IS NULL OR :startPrice <= c.rentalPrice) AND " +
            "(:endPrice IS NULL OR :endPrice >= c.rentalPrice) AND " +
            "(:statusId IS NULL OR :statusId = c.vehicleStatusEntity.id) AND " +
            "(:colorId IS NULL OR :colorId = c.colorEntity.id) AND " +
            "(:seat IS NULL OR :seat = c.seat) AND " +
            "(:luggage IS NULL OR :luggage = c.luggage) AND " +
            "(:modelId IS NULL OR :modelId = c.carModelEntity.id) AND " +
            "(:startYear IS NULL OR :startYear <= c.year) AND " +
            "(:endYear IS NULL OR :endYear >= c.year) AND " +
            "(:brandId IS NULL OR :brandId = c.carModelEntity.brandEntity.id) AND " +
            "(:fuelTypeId IS NULL OR :fuelTypeId = c.fuelTypeEntity.id) AND " +
            "(:shiftTypeId IS NULL OR :shiftTypeId = c.shiftTypeEntity.id) AND " +
            "(:segmentId IS NULL OR :segmentId = c.carSegmentEntity.id)")
    List<CarEntity> findAllFiltered(

            @Param("startPrice") Integer startPrice,
            @Param("endPrice") Integer endPrice,
            @Param("statusId") Integer statusId,
            @Param("colorId") Integer colorId,
            @Param("seat") Integer seat,
            @Param("luggage") Integer luggage,
            @Param("modelId") Integer modelId,
            @Param("startYear") Integer startYear,
            @Param("endYear") Integer endYear,
            @Param("brandId") Integer brandId,
            @Param("fuelTypeId") Integer fuelTypeId,
            @Param("shiftTypeId") Integer shiftTypeId,
            @Param("segmentId") Integer segmentId);

    List<CarEntity> findAllByCarModelEntity_BrandEntity_Id(int brandEntityId);

    boolean existsByLicensePlate(String plate);

    boolean existsByLicensePlateAndIdNot(String plate, int id);


    List<CarEntity> findAllByIsDeleted(boolean isDeleted);

    List<CarEntity> findAllByVehicleStatusEntityId(Integer vehicleStatusId);

    List<CarEntity> findAllByColorEntity_Id(int id);

    List<CarEntity> findAllByRentalPriceBetween(double startPrice, double endPrice);

    List<CarEntity> findAllByCarModelEntity_Id(int id);

    List<CarEntity> findAllByYearBetween(int year1, int year2);

    int countByIsDeleted(boolean isDeleted);

    int countByVehicleStatusEntity_Id(int vehicleStatusId);
}