package source_files.repositories.otp;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.OtpEntities.OtpEntity;

public interface OtpRepository extends JpaRepository<OtpEntity, Integer> {
}
