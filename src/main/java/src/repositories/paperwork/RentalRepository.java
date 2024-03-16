package src.repositories.paperwork;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import src.data.models.paperwork_entities.rentalEntities.RentalEntity;

import java.time.LocalDate;
import java.util.List;


public interface RentalRepository extends JpaRepository<RentalEntity, Integer> {

    @Transactional
    List<RentalEntity> findAllByIsDeleted(boolean isDeleted);

    @Transactional
    List<RentalEntity> findAllByRentalStatusEntity_Id(int statusId);

    // Aktif ve çakışan rentalleri buluyoruz
    @Query("SELECT r FROM RentalEntity r " +
            "WHERE r.isActive = true " +
            "AND ((r.startDate BETWEEN :startDate AND :endDate) OR (r.endDate BETWEEN :startDate AND :endDate) " +
            "OR (:startDate IS NULL AND :endDate BETWEEN r.startDate AND r.endDate))")
    List<RentalEntity> findOverlappingRentals(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    int countByIsDeleted(boolean isDeleted);

    int countByRentalStatusEntity_Id(int statusId);

}
