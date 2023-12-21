package source_files.dataAccess.userRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.userEntities.EmployeeEntity;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Integer> {

    List<EmployeeEntity> findAllBySalaryBetween(double salary1, double salary2);

    List<EmployeeEntity> findAllByIsDeletedFalse();

    List<EmployeeEntity> findAllByIsDeletedTrue();
}
