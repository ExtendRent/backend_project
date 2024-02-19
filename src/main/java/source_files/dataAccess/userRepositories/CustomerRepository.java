package source_files.dataAccess.userRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import source_files.data.models.userEntities.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findByEmailAddress(String emailAddress);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, int id);

    boolean existsByDrivingLicenseNumber(String drivingLicenseNumber);

    boolean existsByDrivingLicenseNumberAndIdNot(String drivingLicenseNumber, int id);

    boolean existsByEmailAddress(String email);

    boolean existsByEmailAddressAndIdNot(String emailAddress, int id);
    @Transactional
    List<CustomerEntity> findAllByIsDeleted(boolean isDeleted);
}
