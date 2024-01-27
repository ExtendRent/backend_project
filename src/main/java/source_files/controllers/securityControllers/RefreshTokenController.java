package source_files.controllers.securityControllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.requests.auth.RefreshTokenRequest;
import source_files.data.responses.JwtToken;
import source_files.data.responses.TResponse;
import source_files.services.userServices.abstracts.AccessTokenService;

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

