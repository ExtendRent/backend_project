package source_files.dataAccess.paperWorkRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;

import java.time.LocalDate;
import java.util.List;


public interface RentalRepository extends JpaRepository<RentalEntity, Integer> {

    List<RentalEntity> findAllByIsDeleted(boolean isDeleted);

    // Aktif ve çakışan rentalleri buluyoruz
    @Query("SELECT r FROM RentalEntity r " +
            "WHERE r.isActive = true " +
            "AND ((r.startDate BETWEEN :startDate AND :endDate) OR (r.endDate BETWEEN :startDate AND :endDate) " +
            "OR (:startDate IS NULL AND :endDate BETWEEN r.startDate AND r.endDate))")
    List<RentalEntity> findOverlappingRentals(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

}
