package source_files.services.userServices;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import source_files.core.services.JwtService;
import source_files.data.models.baseEntities.UserEntity;
import source_files.data.requests.auth.SignInRequest;
import source_files.data.requests.auth.SignUpReqeust;
import source_files.data.responses.JwtToken;
import source_files.services.entityServices.abstracts.userAbstract.UserEntityService;
import source_files.services.userServices.abstracts.AuthenticationService;

@Service
@RequiredArgsConstructor
public class CustomAuthenticationManager implements AuthenticationService {

    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    private final UserEntityService userEntityService;


    @Override
    public JwtToken signUp(SignUpReqeust request) {
        return null;
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