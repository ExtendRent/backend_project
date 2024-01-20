package source_files.services.entityServices.abstracts.otpAbstracts;

import source_files.data.models.OtpEntities.MailTemplateEntity;

public interface MailTemplateEntityService {

    MailTemplateEntity getByTemplateName(String templateName);
}
