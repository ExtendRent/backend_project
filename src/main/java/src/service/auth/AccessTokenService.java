package src.service.auth;

import src.controller.auth.token.request.RefreshTokenRequest;
import src.core.security.model.JwtToken;

public interface AccessTokenService {

    JwtToken refreshToken(RefreshTokenRequest refreshTokenRequest);

}
