package src.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import src.controller.TResponse;
import src.controller.auth.requests.RefreshTokenRequest;
import src.core.security.model.JwtToken;
import src.service.auth.AccessTokenService;

@RestController
@RequestMapping("/api/v1/refresh-token")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final AccessTokenService accessTokenService;

    @PostMapping
    public ResponseEntity<TResponse<JwtToken>> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        accessTokenService.refreshToken(refreshTokenRequest);
        return new ResponseEntity<>(TResponse.<JwtToken>tResponseBuilder()
                .response(accessTokenService.refreshToken(refreshTokenRequest)).build(), HttpStatus.OK
        );
    }
}

