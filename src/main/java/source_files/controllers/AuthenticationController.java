package source_files.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import source_files.data.requests.auth.SignInRequest;
import source_files.data.requests.auth.SignUpReqeust;
import source_files.data.responses.JwtToken;
import source_files.data.responses.TResponse;
import source_files.services.userServices.abstracts.AuthenticationService;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthenticationController {

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

}
