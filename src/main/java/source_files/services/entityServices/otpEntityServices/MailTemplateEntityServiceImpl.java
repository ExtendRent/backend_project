package source_files.services.entityServices.otpEntityServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.OtpEntities.MailTemplateEntity;
import source_files.dataAccess.otpRepositories.MailTemplateRepository;
import source_files.services.entityServices.abstracts.otpAbstracts.MailTemplateEntityService;

@Service
@RequiredArgsConstructor
public class MailTemplateEntityServiceImpl implements MailTemplateEntityService {
    private final MailTemplateRepository mailTemplateRepository;

    @Override
    public MailTemplateEntity getByTemplateName(String templateName) {

        return mailTemplateRepository.findByTemplateName(templateName);
    }
}
