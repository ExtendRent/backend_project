package source_files.services.externalServices;

import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringSubstitutor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import source_files.data.models.OtpEntities.MailTemplateEntity;
import source_files.data.models.OtpEntities.OtpEntity;
import source_files.data.models.baseEntities.UserEntity;
import source_files.services.entityServices.abstracts.otpAbstracts.MailTemplateEntityService;
import source_files.services.entityServices.abstracts.userAbstract.UserEntityService;
import source_files.services.entityServices.otpEntityServices.OtpEntityServiceImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailServiceImpl implements EmailService {
    public static final String EMAIL_CONFIRMATION = "EMAIL_CONFIRMATION";
    private final JavaMailSender javaMailSender;
    private final OtpEntityServiceImpl otpEntityServiceImpl;

    private final MailTemplateEntityService mailTemplateEntityService;

    private final UserEntityService userEntityService;


    private static String formatUserName(UserEntity userEntity) {

        return userEntity.getName().substring(0, 1).toUpperCase() + userEntity.getName().substring(1) + " "
                + userEntity.getSurname().substring(0, 1).toUpperCase() + userEntity.getSurname().substring(1);
    }

    @Override
    public void sendOtp(String email) {
        OtpEntity otpEntity = OtpEntity.otpBuilder()
                .destination(email)
                .verificationToken(UUID.randomUUID().toString())
                .build();
        otpEntityServiceImpl.createOtp(otpEntity);

        String registerConfirmUrl = String.format(
                "%s%s%s",
                "%%s",
                "/api/v1/register/confirm",
                otpEntity.getVerificationToken());

        sendEmail(registerConfirmUrl, email);
    }

    private void sendEmail(String confirmLink, String email) {
        UserEntity userEntity = userEntityService.getByEmailAddress(email);
        MailTemplateEntity mailTemplateEntity = mailTemplateEntityService.getByTemplateName(EMAIL_CONFIRMATION);

        String htmlContent = StringSubstitutor.replace(
                mailTemplateEntity.getContent(),
                new HashMap<>(
                        Map.of(
                                "userName", formatUserName(userEntity),
                                "confirmLink", confirmLink)
                ));

        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

            messageHelper.setFrom(mailTemplateEntity.getMailFrom(), mailTemplateEntity.getMailFromName());
            messageHelper.setTo(email);
            messageHelper.setSubject(mailTemplateEntity.getMailSubject());
            messageHelper.setText(htmlContent, true);
        } catch (Exception e) {
            log.debug("Mail gönderilemedi");
        }

        javaMailSender.send(message);
    }


}
