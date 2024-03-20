package src.repository.otp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import src.core.BaseEntity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder(builderMethodName = "otpBuilder")
@Table(name = "otp")
public class OtpEntity extends BaseEntity {


    @Column(name = "verification_token")
    private String verificationToken;
    @Column(name = "verification_code")
    private String verificationCode;
    @Column(name = "destination")
    private String destination;
}
