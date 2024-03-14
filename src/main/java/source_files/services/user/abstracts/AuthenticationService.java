package source_files.services.user.abstracts;

import source_files.controllers.security.requests.SignInRequest;
import source_files.controllers.security.requests.SignUpReqeust;
import source_files.data.responses.JwtToken;

public interface AuthenticationService {
    void signUp(SignUpReqeust request);

    JwtToken signIn(SignInRequest request);

    boolean isUserTrue(String email, String password);
}
