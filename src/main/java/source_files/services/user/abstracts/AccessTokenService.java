package source_files.services.user.abstracts;

import source_files.controllers.security.requests.RefreshTokenRequest;
import source_files.data.responses.JwtToken;

public interface AccessTokenService {

    JwtToken refreshToken(RefreshTokenRequest refreshTokenRequest);

}
