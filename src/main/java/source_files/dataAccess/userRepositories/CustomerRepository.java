package source_files.dataAccess.userRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.DTO.userDTOs.CustomerDTO;
import source_files.data.models.userEntities.CustomerEntity;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    CustomerEntity findByPhoneNumber();
}
