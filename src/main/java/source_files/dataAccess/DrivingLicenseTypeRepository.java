package source_files.dataAccess;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.DrivingLicenseTypeEntity;

import java.util.List;

public interface DrivingLicenseTypeRepository extends JpaRepository<DrivingLicenseTypeEntity, Integer> {

    List<DrivingLicenseTypeEntity> findAllByIsDeleted(boolean isDeleted);


}
