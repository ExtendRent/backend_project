package source_files.dataAccess.userRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.baseEntities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmailAddressIgnoreCase(String emailAddress);

    int countByIsDeleted(boolean isDeleted);

    List<UserEntity> findAllByIsDeleted(boolean isDeleted);
}
