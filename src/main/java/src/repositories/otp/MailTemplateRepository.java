package src.repositories.otp;

import org.springframework.data.jpa.repository.JpaRepository;
import src.data.models.otp_entities.MailTemplateEntity;

public interface MailTemplateRepository extends JpaRepository<MailTemplateEntity, Integer> {
    MailTemplateEntity findByTemplateName(String templateName);
}
