package source_files.dataAccess.userRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.userEntities.CustomerEntity;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    Optional<CustomerEntity> findByPhoneNumber(String phoneNumber);

    List<CustomerEntity> findAllByIsDeletedFalse();

    List<CustomerEntity> findAllByIsDeletedTrue();
}
