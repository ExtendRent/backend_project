package src.service.auth;

import src.controller.auth.requests.RefreshTokenRequest;
import src.core.security.model.JwtToken;

public interface AccessTokenService {

    JwtToken refreshToken(RefreshTokenRequest refreshTokenRequest);

}
