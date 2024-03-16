package src.services.otp;

import src.data.models.otp_entities.MailTemplateEntity;

public interface MailTemplateEntityService {

    MailTemplateEntity getByTemplateName(String templateName);
}
