package src.controllers.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import src.controllers.auth.requests.SignInRequest;
import src.controllers.auth.requests.SignUpReqeust;
import src.data.global_responses.JwtToken;
import src.data.global_responses.TResponse;
import src.services.auth.AuthenticationService;
import src.services.external.EmailService;


@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthenticationController {
    private final EmailService emailService;
    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    ResponseEntity<Void> signUp(@Valid @RequestBody SignUpReqeust request) {
        authenticationService.signUp(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/signin")
    ResponseEntity<TResponse<JwtToken>> singIn(@Valid @RequestBody SignInRequest request) {
        return new ResponseEntity<>(TResponse.<JwtToken>tResponseBuilder()
                .response(authenticationService.signIn(request)).build(), HttpStatus.OK
        );
    }

    @GetMapping("/isUserTrue")
    public ResponseEntity<TResponse<Boolean>> isCustomerTrue(
            @RequestParam String email, @RequestParam String password) {
        return new ResponseEntity<>(TResponse.<Boolean>tResponseBuilder()
                .response(authenticationService.isUserTrue(email, password))
                .build(), HttpStatus.OK
        );
    }


}
