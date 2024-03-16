package src.controllers.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controllers.auth.requests.RefreshTokenRequest;
import src.data.global_responses.JwtToken;
import src.data.global_responses.TResponse;
import src.services.auth.AccessTokenService;

@RestController
@RequestMapping("/api/v1/refresh-token")
@RequiredArgsConstructor
@CrossOrigin
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

