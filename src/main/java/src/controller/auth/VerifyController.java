package src.controller.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import src.controller.TResponse;
import src.core.security.model.JwtToken;

@RestController
@RequestMapping("/api/v1/verify")

@RequiredArgsConstructor
public class VerifyController {

    @GetMapping("/email")
    ResponseEntity<TResponse<JwtToken>> verifyEmailAddress(@RequestParam("token") String token) {

        return null;
    }
}
