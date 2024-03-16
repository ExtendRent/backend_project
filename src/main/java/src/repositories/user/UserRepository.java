package src.repositories.user;

import org.springframework.data.jpa.repository.JpaRepository;
import src.data.models.base_entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmailAddressIgnoreCase(String emailAddress);

    int countByIsDeleted(boolean isDeleted);

    List<UserEntity> findAllByIsDeleted(boolean isDeleted);

    boolean existsByEmailAddressIgnoreCase(String email);
}
