package source_files.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import source_files.data.requests.auth.SignInRequest;
import source_files.data.requests.auth.SignUpReqeust;
import source_files.data.responses.JwtToken;
import source_files.services.userServices.abstracts.AuthenticationService;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/signup")
    public JwtToken signUp(SignUpReqeust request) {
        authenticationService.signUp(request);
        return null;
    }

    @PostMapping("/signin")
    public JwtToken signIn(@Valid @RequestBody SignInRequest request) {
        return authenticationService.signIn(request);
    }
}
