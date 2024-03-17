package src.repository.otp.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailTemplateEntityServiceImpl implements MailTemplateEntityService {
    private final MailTemplateRepository mailTemplateRepository;

    @Override
    public MailTemplateEntity getByTemplateName(String templateName) {

        return mailTemplateRepository.findByTemplateName(templateName);
    }
}
