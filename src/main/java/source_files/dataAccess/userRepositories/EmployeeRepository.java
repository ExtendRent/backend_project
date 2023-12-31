package source_files.dataAccess.userRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.userEntities.EmployeeEntity;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {
    Optional<EmployeeEntity> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, int id);

    boolean existsByEmailAddress(String emailAddress);

    boolean existsByEmailAddressAndIdNot(String emailAddress, int id);

    List<EmployeeEntity> findAllBySalaryBetween(double salary1, double salary2);

    List<EmployeeEntity> findAllByIsDeletedFalse();

    List<EmployeeEntity> findAllByIsDeletedTrue();
}
