package source_files.controllers.security;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.controllers.security.requests.SignInRequest;
import source_files.controllers.security.requests.SignUpReqeust;
import source_files.data.responses.JwtToken;
import source_files.data.responses.TResponse;
import source_files.services.external.EmailService;
import source_files.services.user.abstracts.AuthenticationService;


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
