package source_files.dataAccess.userRepositories;


import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.paperWorkEntities.rentalEntities.RentalEntity;
import source_files.data.models.userEntities.AdminEntity;
import source_files.data.models.vehicleEntities.vehicleFeatures.CarFeatures.BrandEntity;

import java.util.List;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    List<AdminEntity> findAllByIsDeletedFalse();
    List<AdminEntity> findAllByIsDeletedTrue();
}
