package source_files.servicesTests.userTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import source_files.data.requests.auth.RefreshTokenRequest;
import source_files.data.requests.auth.SignInRequest;
import source_files.data.requests.auth.SignUpReqeust;
import source_files.data.requests.userRequests.CreateAdminRequest;
import source_files.data.responses.JwtToken;
import source_files.services.entityServices.abstracts.userAbstract.UserEntityService;
import source_files.services.externalServices.EmailService;
import source_files.services.userServices.CustomAuthenticationManager;
import source_files.services.userServices.abstracts.AdminService;
import source_files.services.userServices.abstracts.CustomerService;
import source_files.services.userServices.abstracts.EmployeeService;
import source_files.core.services.JwtService;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.models.baseEntities.UserEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CustomAuthenticationManagerTest {

    @Mock
    private AdminService adminService;

    @Mock
    private EmployeeService employeeService;

    @Mock
    private CustomerService customerService;

    @Mock
    private ModelMapperService mapper;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private UserEntityService userEntityService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private CustomAuthenticationManager customAuthenticationManager;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSignUp_Admin() {
        SignUpReqeust signUpReqeust = SignUpReqeust.builder().build();
        when(mapper.forRequest().map(signUpReqeust, CreateAdminRequest.class)).thenReturn(new CreateAdminRequest());

        customAuthenticationManager.signUp(signUpReqeust);

        verify(adminService, times(1)).create(any(CreateAdminRequest.class));
    }

    @Test
    public void testSignIn_ValidCredentials() {
        SignInRequest signInRequest = new SignInRequest();
        UserEntity userEntity = new UserEntity();
        when(userEntityService.getByEmailAddress(signInRequest.getEmail())).thenReturn(userEntity);
        when(customAuthenticationManager.isUserTrue(signInRequest.getEmail(), signInRequest.getPassword())).thenReturn(true);
        when(jwtService.generateToken(userEntity)).thenReturn("mockToken");

        JwtToken jwtToken = customAuthenticationManager.signIn(signInRequest);

        assertNotNull(jwtToken);
        assertEquals("mockToken", jwtToken.getToken());
    }

    @Test
    public void testRefreshToken_ValidToken() {
        RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
        UserEntity userEntity = new UserEntity();
        when(userEntityService.getByEmailAddress(refreshTokenRequest.getEmail())).thenReturn(userEntity);
        when(jwtService.isTokenValid(refreshTokenRequest.getToken(), userEntity)).thenReturn(true);
        when(jwtService.generateToken(userEntity)).thenReturn("newMockToken");

        JwtToken newJwtToken = customAuthenticationManager.refreshToken(refreshTokenRequest);

        assertNotNull(newJwtToken);
        assertEquals("newMockToken", newJwtToken.getToken());
    }

}

