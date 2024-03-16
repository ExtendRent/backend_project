package src.services.otp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.data.models.otp_entities.MailTemplateEntity;
import src.repositories.otp.MailTemplateRepository;

@Service
@RequiredArgsConstructor
public class MailTemplateEntityServiceImpl implements MailTemplateEntityService {
    private final MailTemplateRepository mailTemplateRepository;

    @Override
    public MailTemplateEntity getByTemplateName(String templateName) {

        return mailTemplateRepository.findByTemplateName(templateName);
    }
}
