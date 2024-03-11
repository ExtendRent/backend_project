package source_files.services.entityServices.otpEntityServices;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import source_files.data.models.OtpEntities.OtpEntity;
import source_files.dataAccess.otpRepositories.OtpRepository;
import source_files.services.entityServices.abstracts.otpAbstracts.OtpEntityService;

@Service
@RequiredArgsConstructor
public class OtpEntityServiceImpl implements OtpEntityService {
    private final OtpRepository otpRepository;

    @Override
    public void createOtp(OtpEntity otpEntity) {
        otpRepository.save(otpEntity);
    }
}
