package src.repository.otp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OtpEntityServiceImpl implements OtpEntityService {
    private final OtpRepository otpRepository;

    @Override
    public void createOtp(OtpEntity otpEntity) {
        otpRepository.save(otpEntity);
    }
}
