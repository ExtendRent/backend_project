package source_files.services.userServices.abstracts;

import source_files.data.requests.auth.RefreshTokenRequest;
import source_files.data.responses.JwtToken;

public interface AccessTokenService {

    JwtToken refreshToken(RefreshTokenRequest refreshTokenRequest);

}
