package source_files.dataAccess.userRepositories;


import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.userEntities.AdminEntity;

import java.util.List;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {
    List<AdminEntity> findAllByIsDeletedFalse();

    List<AdminEntity> findAllByIsDeletedTrue();
}
