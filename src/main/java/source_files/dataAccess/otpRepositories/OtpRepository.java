package source_files.dataAccess.otpRepositories;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.OtpEntities.OtpEntity;

public interface OtpRepository extends JpaRepository<OtpEntity, Integer> {
}
