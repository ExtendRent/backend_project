package src.repository.item.license;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrivingLicenseTypeRepository extends JpaRepository<DrivingLicenseTypeEntity, Integer> {

    List<DrivingLicenseTypeEntity> findAllByIsDeleted(boolean isDeleted);

    boolean existsByNameIgnoreCaseAndIdNot(String name, int id);

    boolean existsByNameIgnoreCase(String name);

}
