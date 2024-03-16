package src.services.auth;

import src.controllers.auth.requests.SignInRequest;
import src.controllers.auth.requests.SignUpReqeust;
import src.data.global_responses.JwtToken;

public interface AuthenticationService {
    void signUp(SignUpReqeust request);

    JwtToken signIn(SignInRequest request);

    boolean isUserTrue(String email, String password);
}
