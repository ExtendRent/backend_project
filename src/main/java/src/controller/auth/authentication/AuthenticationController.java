package src.controller.auth.authentication;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controller.TResponse;
import src.controller.auth.authentication.requests.SignInRequest;
import src.controller.auth.authentication.requests.SignUpReqeust;
import src.core.security.model.JwtToken;
import src.service.auth.AuthenticationService;
import src.service.external.EmailService;

import static src.controller.auth.authentication.LogConstant.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private final EmailService emailService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    ResponseEntity<Void> signUp(@Valid @RequestBody SignUpReqeust request) {
        logger.info(USER_SIGN_UP_REQUEST_RECEIVED, request.getEmailAddress());
        authenticationService.signUp(request);
        logger.info(USER_SIGN_UP_SUCCESSFUL, request.getEmailAddress());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/signin")
    ResponseEntity<TResponse<JwtToken>> signIn(@Valid @RequestBody SignInRequest request) {
        logger.info(USER_SIGN_IN_REQUEST_RECEIVED, request.getEmail());
        TResponse<JwtToken> response = TResponse.<JwtToken>tResponseBuilder()
                .response(authenticationService.signIn(request)).build();
        logger.info(USER_SIGN_IN_SUCCESSFUL, request.getEmail());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/isUserTrue")
    public ResponseEntity<TResponse<Boolean>> isCustomerTrue(
            @RequestParam String email, @RequestParam String password) {
        logger.info(CHECKING_USER_CREDENTIALS, email);
        TResponse<Boolean> response = TResponse.<Boolean>tResponseBuilder()
                .response(authenticationService.isUserTrue(email, password))
                .build();
        logger.info(USER_CREDENTIALS_CHECKED, email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
