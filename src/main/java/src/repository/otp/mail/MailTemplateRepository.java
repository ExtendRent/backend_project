package src.repository.otp.mail;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MailTemplateRepository extends JpaRepository<MailTemplateEntity, Integer> {
    MailTemplateEntity findByTemplateName(String templateName);
}
