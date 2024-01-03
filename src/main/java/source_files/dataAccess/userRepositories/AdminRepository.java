package source_files.dataAccess.userRepositories;


import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.userEntities.AdminEntity;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    Optional<AdminEntity> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmailAddress(String email);

    boolean existsByEmailAddressAndIdNot(String emailAddress, int id);

    boolean existsByPhoneNumberAndIdNot(String phoneNumber, int id);

    List<AdminEntity> findBySalaryGreaterThanEqual(Double salary);

    List<AdminEntity> findAllByIsDeletedFalse();

    List<AdminEntity> findAllByIsDeletedTrue();


}
