package source_files.repositories.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import source_files.data.models.userEntities.AdminEntity;

import java.util.List;
import java.util.Optional;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {

    @Transactional
    Optional<AdminEntity> findByEmailAddress(String emailAddress);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean existsByEmailAddressIgnoreCase(String email);

    boolean existsByEmailAddressIgnoreCaseAndIdNot(String emailAddress, int id);

    boolean existsByPhoneNumberIgnoreCaseAndIdNot(String phoneNumber, int id);

    List<AdminEntity> findAllByIsDeleted(boolean isDeleted);

    int countByIsDeleted(boolean isDeleted);

}
