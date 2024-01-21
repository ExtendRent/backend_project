package source_files.services.userServices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import source_files.core.services.JwtService;
import source_files.data.DTO.Mappers.ModelMapperService;
import source_files.data.models.baseEntities.UserEntity;
import source_files.data.requests.auth.SignInRequest;
import source_files.data.requests.auth.SignUpReqeust;
import source_files.data.requests.userRequests.AddAdminRequest;
import source_files.data.requests.userRequests.AddCustomerRequest;
import source_files.data.requests.userRequests.AddEmployeeRequest;
import source_files.data.responses.JwtToken;
import source_files.services.entityServices.abstracts.userAbstract.UserEntityService;
import source_files.services.externalServices.EmailService;
import source_files.services.userServices.abstracts.AdminService;
import source_files.services.userServices.abstracts.AuthenticationService;
import source_files.services.userServices.abstracts.CustomerService;
import source_files.services.userServices.abstracts.EmployeeService;

@Service
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationService {

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final UserEntityService userEntityService;

    private final ModelMapperService modelMapperService;

    private final AdminService adminService;

    private final EmployeeService employeeService;

    private final CustomerService customerService;

    private final EmailService emailService;

    @Override
    public void signUp(SignUpReqeust request) {
        switch (request.getAuthority()) {
            case ADMIN:
                this.adminService.add(this.modelMapperService.forRequest().map(request, AddAdminRequest.class));
            case EMPLOYEE:
                this.employeeService.add(this.modelMapperService.forRequest().map(request, AddEmployeeRequest.class));
            case CUSTOMER:
                this.customerService.add(this.modelMapperService.forRequest().map(request, AddCustomerRequest.class));
                emailService.sendOtp(request.getEmailAddress());
        }

    }

    public JwtToken signIn(SignInRequest request) {
        UserEntity userEntity = userEntityService.getByEmailAddress(request.getEmail());

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        if (authentication.isAuthenticated()) {
            String token = jwtService.generateToken(userEntity);
            return JwtToken.builder().token(token).build();
        }
        throw new RuntimeException("Bilgiler hatalı");

    }
}