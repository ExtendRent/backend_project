package source_files.repositories.otp;

import org.springframework.data.jpa.repository.JpaRepository;
import source_files.data.models.OtpEntities.MailTemplateEntity;

public interface MailTemplateRepository extends JpaRepository<MailTemplateEntity, Integer> {
    MailTemplateEntity findByTemplateName(String templateName);
}
