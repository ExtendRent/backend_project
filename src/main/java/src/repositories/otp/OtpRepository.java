package src.repositories.otp;

import org.springframework.data.jpa.repository.JpaRepository;
import src.data.models.otp_entities.OtpEntity;

public interface OtpRepository extends JpaRepository<OtpEntity, Integer> {
}
