package src.repository.user.customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import src.service.user.model.DefaultUserStatus;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findByEmailAddress(String emailAddress);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, int id);

    boolean existsByDrivingLicenseNumberIgnoreCase(String drivingLicenseNumber);

    boolean existsByDrivingLicenseNumberIgnoreCaseAndIdNot(String drivingLicenseNumber, int id);

    boolean existsByEmailAddressIgnoreCase(String email);

    boolean existsByEmailAddressIgnoreCaseAndIdNot(String emailAddress, int id);


    List<CustomerEntity> findAllByIsDeleted(boolean isDeleted);

    int countByIsDeleted(boolean isDeleted);

    @Query("SELECT COUNT(c) FROM CustomerEntity c WHERE c.status = :status")
    int countByStatus(@Param("status") DefaultUserStatus status);
}
