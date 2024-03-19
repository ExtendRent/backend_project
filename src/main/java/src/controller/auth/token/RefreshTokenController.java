package src.controller.auth.token;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import src.controller.TResponse;
import src.controller.auth.token.requests.RefreshTokenRequest;
import src.core.security.model.JwtToken;
import src.service.auth.AccessTokenService;

import static src.controller.auth.token.LogConstant.REFRESH_TOKEN_REQUEST_RECEIVED;
import static src.controller.auth.token.LogConstant.REFRESH_TOKEN_SUCCESSFUL;

@RestController
@RequestMapping("/api/v1/refresh-token")
@RequiredArgsConstructor
public class RefreshTokenController {
    private static final Logger logger = LoggerFactory.getLogger(RefreshTokenController.class);

    private final AccessTokenService accessTokenService;

    @PostMapping
    public ResponseEntity<TResponse<JwtToken>> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        logger.info(REFRESH_TOKEN_REQUEST_RECEIVED, refreshTokenRequest.getToken());
        JwtToken refreshedToken = accessTokenService.refreshToken(refreshTokenRequest);
        logger.info(REFRESH_TOKEN_SUCCESSFUL);
        return new ResponseEntity<>(TResponse.<JwtToken>tResponseBuilder()
                .response(refreshedToken).build(), HttpStatus.OK
        );
    }
}
