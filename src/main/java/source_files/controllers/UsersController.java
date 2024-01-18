package source_files.controllers;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import source_files.core.services.JwtService;
import source_files.data.requests.auth.LoginRequest;
import source_files.data.requests.userRequests.AddCustomerRequest;
import source_files.services.userServices.abstracts.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UsersController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping
    public void register(@RequestBody AddCustomerRequest request) {
        userService.register(request);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        if (authentication.isAuthenticated()) {

            // jwt oluştur.
            Map<String, Object> claims = new HashMap<>();
            return jwtService.generateToken(request.getEmail(), claims);
        }
        throw new RuntimeException("Bilgiler hatalı");
    }
}
