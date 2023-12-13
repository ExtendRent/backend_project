package source_files.dataAccess.userRepositories;


import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.userEntities.AdminEntity;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
}
