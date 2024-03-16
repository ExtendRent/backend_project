package src.services.auth;

import src.controllers.auth.requests.RefreshTokenRequest;
import src.data.global_responses.JwtToken;

public interface AccessTokenService {

    JwtToken refreshToken(RefreshTokenRequest refreshTokenRequest);

}
