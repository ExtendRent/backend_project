package src.services.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import src.controllers.auth.requests.RefreshTokenRequest;
import src.controllers.auth.requests.SignInRequest;
import src.controllers.auth.requests.SignUpReqeust;
import src.core.exception.DataNotFoundException;
import src.core.security.JwtService;
import src.data.global_responses.JwtToken;
import src.data.models.base_entities.UserEntity;
import src.services.external.EmailService;
import src.services.user.UserEntityService;
import src.services.user.admin.AdminService;
import src.services.user.customer.CustomerService;
import src.services.user.employee.EmployeeService;

import static src.core.exception.exception_types.NotFoundExceptionType.USER_ROLE_NOT_FOUND;

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
            case ADMIN -> this.adminService.create(request.forAdmin());
            case EMPLOYEE -> this.employeeService.create(request.forEmployee());
            case CUSTOMER -> {
                this.customerService.create(request.forCustomer());
                emailService.sendOtp(request.getEmailAddress());
            }
            default -> throw new DataNotFoundException(USER_ROLE_NOT_FOUND);
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