package source_files.services.userServices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import source_files.core.services.JwtService;
import source_files.data.models.baseEntities.UserEntity;
import source_files.data.requests.auth.RefreshTokenRequest;
import source_files.data.requests.auth.SignInRequest;
import source_files.data.requests.auth.SignUpReqeust;
import source_files.data.responses.JwtToken;
import source_files.exception.DataNotFoundException;
import source_files.services.entityServices.abstracts.userAbstract.UserEntityService;
import source_files.services.externalServices.EmailService;
import source_files.services.userServices.abstracts.*;

import static source_files.exception.exceptionTypes.NotFoundExceptionType.USER_ROLE_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CustomAuthenticationServiceImpl implements AuthenticationService, AccessTokenService {
    private final AdminService adminService;
    private final EmployeeService employeeService;
    private final CustomerService customerService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserEntityService userEntityService;
    private final EmailService emailService;

    @Override
    public void signUp(SignUpReqeust request) {

        switch (request.getAuthority()) {
            case ADMIN:
                this.adminService.create(request.forAdmin());
                break;
            case EMPLOYEE:
                this.employeeService.create(request.forEmployee());
                break;
            case CUSTOMER:
                this.customerService.create(request.forCustomer());
                emailService.sendOtp(request.getEmailAddress());
                break;
            default:
                throw new DataNotFoundException(USER_ROLE_NOT_FOUND);
        }
    }

    @Transactional
    public JwtToken signIn(SignInRequest request) {
        UserEntity userEntity = userEntityService.getByEmailAddress(request.getEmail());

        if (isUserTrue(request.getEmail(), request.getPassword())) {
            String token = jwtService.generateToken(userEntity);
            return JwtToken.builder().token(token).build();
        }
        throw new RuntimeException("Bilgiler hatalı");
    }

    @Override
    public JwtToken refreshToken(RefreshTokenRequest refreshTokenRequest) {
        UserEntity userEntity = userEntityService.getByEmailAddress(refreshTokenRequest.getEmail());
        if (jwtService.isTokenValid(refreshTokenRequest.getToken(), userEntity)) {
            String newAccessToken = jwtService.generateToken(userEntity);
            return JwtToken.builder().token(newAccessToken).build();
        }
        throw new RuntimeException("Bilgiler hatalı");
    }

    @Override
    public boolean isUserTrue(String emailAddress, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(emailAddress, password)
        );
        return authentication.isAuthenticated();
    }
}