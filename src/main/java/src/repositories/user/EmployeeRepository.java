package src.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import src.data.models.user_entities.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    @Transactional
    Optional<EmployeeEntity> findByEmailAddress(String emailAddress);

    @Transactional
    Optional<EmployeeEntity> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, int id);

    boolean existsByEmailAddressIgnoreCase(String emailAddress);

    boolean existsByEmailAddressIgnoreCaseAndIdNot(String emailAddress, int id);

    List<EmployeeEntity> findAllBySalaryBetween(double salary1, double salary2);

    @Transactional
    List<EmployeeEntity> findAllByIsDeleted(boolean isDeleted);

    int countByIsDeleted(boolean isDeleted);
}
