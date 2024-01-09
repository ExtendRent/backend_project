package source_files.dataAccess.userRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.userEntities.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, int id);

    boolean existsByDrivingLicenseNumber(String drivingLicenseNumber);

    boolean existsByDrivingLicenseNumberAndIdNot(String drivingLicenseNumber, int id);

    boolean existsByEmailAddress(String email);

    boolean existsByEmailAddressAndIdNot(String emailAddress, int id);

    List<CustomerEntity> findAllByIsDeletedFalse();

    List<CustomerEntity> findAllByIsDeletedTrue();
}
