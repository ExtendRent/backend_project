package source_files.data.models.OtpEntities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import source_files.data.models.baseEntities.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "mail_templates")
public class MailTemplateEntity extends BaseEntity {

    @Column(name = "template_name")
    private String templateName;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "mail_subject")
    private String mailSubject;
    @Column(name = "mail_from")
    private String mailFrom;
    @Column(name = "mail_from_name")
    private String mailFromName;
}
