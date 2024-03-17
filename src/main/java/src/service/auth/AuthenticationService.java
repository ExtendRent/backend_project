package src.service.auth;

import src.controller.auth.requests.SignInRequest;
import src.controller.auth.requests.SignUpReqeust;
import src.core.security.model.JwtToken;

public interface AuthenticationService {
    void signUp(SignUpReqeust request);

    JwtToken signIn(SignInRequest request);

    boolean isUserTrue(String email, String password);
}
