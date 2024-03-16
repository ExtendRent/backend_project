package src.services.otp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import src.data.models.otp_entities.OtpEntity;
import src.repositories.otp.OtpRepository;

@Service
@RequiredArgsConstructor
public class OtpEntityServiceImpl implements OtpEntityService {
    private final OtpRepository otpRepository;

    @Override
    public void createOtp(OtpEntity otpEntity) {
        otpRepository.save(otpEntity);
    }
}
